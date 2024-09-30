package com.sheldon.elasticsearch.core.injector.method;

import co.elastic.clients.elasticsearch._types.AcknowledgedResponse;
import co.elastic.clients.elasticsearch.indices.*;
import co.elastic.clients.transport.endpoints.BooleanResponse;
import com.sheldon.elasticsearch.core.AbstractBaseObject;
import com.sheldon.elasticsearch.core.XContentConverter;
import com.sheldon.elasticsearch.core.annotation.DocumentAlias;
import com.sheldon.elasticsearch.core.annotation.Document;
import com.sheldon.elasticsearch.core.constant.Constants;
import com.sheldon.elasticsearch.core.constant.RollOverTypeEnum;
import com.sheldon.elasticsearch.core.exception.ClassInaccuracyException;
import com.sheldon.elasticsearch.core.injector.AbstractMethod;
import org.elasticsearch.common.Strings;
import org.elasticsearch.xcontent.XContentBuilder;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Initialize<T extends AbstractBaseObject> extends AbstractMethod<T> {
    @Override
    public CompletableFuture<?> invoke(Object... args) throws ClassInaccuracyException, IOException, InstantiationException, IllegalAccessException {
        if (args.length < 2) {
            throw new IllegalArgumentException(Constants.ILLEGAL_ARGUMENT_EXCEPTION_INITIALIZE);
        }
        T doc = (T) args[0];
        boolean alias = (boolean) args[1];
        XContentBuilder builder = XContentConverter.object2Builder(doc);
        Document document = getDocument(doc);
        List<String> indies = new ArrayList<>();
        DocumentAlias as = document.alias();
        if (alias && null != as) {
            indies.add(document.indexName() + RollOverTypeEnum.getRollOverSuffix(as.rollOverType(), new Date()));
            Stream.of(new Date[as.preIndexCount()]).reduce(new Date(), (d1, d2) -> {
                d1 = as.rollOverType().getPrev().apply(d1);
                indies.add(document.indexName() + RollOverTypeEnum.getRollOverSuffix(as.rollOverType(), d1));
                return d1;
            });
            Stream.of(new Date[as.postIndexCount()]).reduce(new Date(), (d1, d2) -> {
                d1 = as.rollOverType().getNext().apply(d1);
                indies.add(document.indexName() + RollOverTypeEnum.getRollOverSuffix(as.rollOverType(), d1));
                return d1;
            });
        } else if (null != as) {
            indies.add(document.indexName() + RollOverTypeEnum.getRollOverSuffix(as.rollOverType(), new Date()));
        } else {
            indies.add(document.indexName());
        }
        CompletableFuture<? extends AcknowledgedResponse>[] cfs = indies.stream().map(index -> createOrUpdateIndex(index, document, builder)).toArray(CompletableFuture[]::new);
        CompletableFuture<Void> allOfFuture = CompletableFuture.allOf(cfs);
        return CompletableFuture.supplyAsync(() -> {
            try {
                allOfFuture.get();
                return Arrays.stream(cfs).flatMapToInt(cf -> {
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
        } else{
            PutMappingRequest.Builder pmrBuilder = new PutMappingRequest.Builder();
            pmrBuilder.index(index).withJson(new StringReader(Strings.toString(builder)));
            return client.indices().putMapping(pmrBuilder.build());
        }
    }
}
