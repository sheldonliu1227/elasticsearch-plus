package com.sheldon.elasticsearch.plus.core.injector.method;

import co.elastic.clients.elasticsearch._types.AcknowledgedResponse;
import co.elastic.clients.elasticsearch.indices.Alias;
import co.elastic.clients.elasticsearch.indices.CreateIndexRequest;
import co.elastic.clients.elasticsearch.indices.ExistsRequest;
import co.elastic.clients.elasticsearch.indices.PutMappingRequest;
import co.elastic.clients.transport.endpoints.BooleanResponse;
import com.sheldon.elasticsearch.plus.core.AbstractBaseObject;
import com.sheldon.elasticsearch.plus.core.annotation.Document;
import com.sheldon.elasticsearch.plus.core.annotation.DocumentAlias;
import com.sheldon.elasticsearch.plus.core.constant.Constants;
import com.sheldon.elasticsearch.plus.core.constant.RollOverTypeEnum;
import com.sheldon.elasticsearch.plus.core.exception.ClassInaccuracyException;
import com.sheldon.elasticsearch.plus.core.injector.AbstractMethod;
import com.sheldon.elasticsearch.plus.core.xcontent.ClassParser;
import org.elasticsearch.common.Strings;
import org.elasticsearch.xcontent.XContentBuilder;
import org.elasticsearch.xcontent.XContentFactory;

import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Initialize<T extends AbstractBaseObject> extends AbstractMethod<T> {
    @Override
    public CompletableFuture<?> invoke(Object... args) throws ClassInaccuracyException, IOException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, ClassNotFoundException {
        if (args.length < 2) {
            throw new IllegalArgumentException(Constants.ILLEGAL_ARGUMENT_EXCEPTION_INITIALIZE);
        }
        T doc = (T) args[0];
        boolean alias = (boolean) args[1];
        XContentBuilder builder = new ClassParser(doc.getClass()).parse(XContentFactory.jsonBuilder().startObject());
        Document document = getDocument(doc);
        List<String> indies = new ArrayList<>();
        DocumentAlias as = document.alias();
        if (alias && null != as) {
            indies.add(document.indexName() + RollOverTypeEnum.getRollOverSuffix(as.rollOverType(), new Date()));
            indies.addAll(getIndiesByAlias(as.rollOverType(), false, as.preIndexCount(), document.indexName()));
            indies.addAll(getIndiesByAlias(as.rollOverType(), true, as.postIndexCount(), document.indexName()));
        } else if (null != as) {
            indies.add(document.indexName() + RollOverTypeEnum.getRollOverSuffix(as.rollOverType(), new Date()));
        } else {
            indies.add(document.indexName());
        }
        List<? extends CompletableFuture<? extends AcknowledgedResponse>> cfs = indies.stream().map(index -> createOrUpdateIndex(index, document, builder)).collect(Collectors.toList());
        CompletableFuture<Void> allOfFuture = CompletableFuture.allOf(cfs.toArray(new CompletableFuture[0]));
        return CompletableFuture.supplyAsync(() -> {
            try {
                allOfFuture.get();
                return cfs.stream().flatMapToInt(cf -> {
                    try {
                        return IntStream.of(cf.get().acknowledged() ? 1 : 0);
                    } catch (Exception e) {
                        return IntStream.of(0);
                    }
                }).sum();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private List<String> getIndiesByAlias(RollOverTypeEnum type, Boolean direction, int limit, String prefix) {
        Date now = new Date();
        return Stream.iterate(0, n -> n + 1)
                .limit(limit)
                .reduce(
                        new ArrayList<Date>(), // 初始List
                        (list, last) -> {
                            list.add(type.getOffset().apply(now, direction));
                            return list;
                        },
                        (list1, list2) -> {
                            list1.addAll(list2);
                            return list1;
                        })
                .stream().map(
                        date -> prefix + RollOverTypeEnum.getRollOverSuffix(type, date)
                ).collect(Collectors.toList());
    }

    private CompletableFuture<? extends AcknowledgedResponse> createOrUpdateIndex(String index, Document document, XContentBuilder builder) {
        ExistsRequest existsRequest = new ExistsRequest.Builder().index(index).build();
        CompletableFuture<BooleanResponse> existsResponse = client.indices().exists(existsRequest);
        boolean isExists;
        try {
            isExists = !existsResponse.get().value();
        } catch (InterruptedException | ExecutionException e) {
            return CompletableFuture.supplyAsync(() -> () -> false);
        }
        if (isExists) {
            // 创建索引
            CreateIndexRequest.Builder cirBuilder = new CreateIndexRequest.Builder()
                    .index(index)
                    .settings(s -> s.numberOfShards(String.valueOf(document.shards()))
                            .numberOfReplicas(String.valueOf(document.replicas())))
                    .mappings(m -> m.withJson(new StringReader(Strings.toString(builder))));
            DocumentAlias documentAlias = document.alias();
            if (documentAlias != null) {
                cirBuilder.aliases(documentAlias.indexAlias(), new Alias.Builder().build());
            }
            return CompletableFuture.supplyAsync(() -> {
                try {
                    return client.indices().create(cirBuilder.build()).get()::acknowledged;
                } catch (Exception e) {
                    return () -> false;
                }
            });
        } else {
            PutMappingRequest.Builder pmrBuilder = new PutMappingRequest.Builder();
            pmrBuilder.index(index).withJson(new StringReader(Strings.toString(builder)));
            return client.indices().putMapping(pmrBuilder.build());
        }
    }
}
