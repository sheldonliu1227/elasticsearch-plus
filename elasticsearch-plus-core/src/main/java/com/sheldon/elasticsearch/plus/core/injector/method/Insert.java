package com.sheldon.elasticsearch.core.injector.method;

import com.sheldon.elasticsearch.core.AbstractBaseObject;
import com.sheldon.elasticsearch.core.injector.AbstractMethod;

import java.util.concurrent.CompletableFuture;

public class Insert<T extends AbstractBaseObject> extends AbstractMethod<T> {
    @Override
    public CompletableFuture<?> invoke(Object... args) {
        for (Object arg : args) {
            T doc = (T) arg;

        }
        return null;
    }
}
