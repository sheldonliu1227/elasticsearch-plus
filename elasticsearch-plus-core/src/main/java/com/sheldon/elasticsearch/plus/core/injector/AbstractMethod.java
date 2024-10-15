package com.sheldon.elasticsearch.plus.core.injector;

import co.elastic.clients.elasticsearch.ElasticsearchAsyncClient;
import com.sheldon.elasticsearch.plus.core.AbstractBaseObject;
import com.sheldon.elasticsearch.plus.core.annotation.Document;
import com.sheldon.elasticsearch.plus.core.exception.ClassInaccuracyException;
import com.sheldon.elasticsearch.plus.core.toolkit.MetaInfoUtil;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.*;

public abstract class AbstractMethod<T extends AbstractBaseObject> {
    protected ElasticsearchAsyncClient client;

    protected Document getDocument(T doc) throws ClassInaccuracyException {
        MetaInfoUtil.assertDocumentAnnotation(doc.getClass());
        return doc.getClass().getAnnotation(Document.class);
    }


    public abstract CompletableFuture<?> invoke(Object... args) throws ClassInaccuracyException, IOException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, ClassNotFoundException;
}
