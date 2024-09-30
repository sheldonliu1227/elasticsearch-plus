package com.sheldon.elasticsearch.core.injector;

import co.elastic.clients.elasticsearch.ElasticsearchAsyncClient;
import com.sheldon.elasticsearch.core.AbstractBaseObject;
import com.sheldon.elasticsearch.core.ElasticSearchConfiguration;
import com.sheldon.elasticsearch.core.annotation.Document;
import com.sheldon.elasticsearch.core.constant.Constants;
import com.sheldon.elasticsearch.core.exception.ClassInaccuracyException;

import java.io.IOException;
import java.util.concurrent.*;

public abstract class AbstractMethod<T> {
    protected ElasticSearchConfiguration elasticSearchConfiguration;
    protected ElasticsearchAsyncClient client;

    protected Document getDocument(T doc) {
        if (null == doc || !doc.getClass().isAnnotationPresent(Document.class)) {
            throw new IllegalArgumentException(Constants.CLASS_INACCURACY_DOC_INVALID);
        }
        return doc.getClass().getAnnotation(Document.class);
    }


    public abstract CompletableFuture<?> invoke(Object... args) throws ClassInaccuracyException, IOException, InstantiationException, IllegalAccessException;
}
