package com.sheldon.elasticsearch.core.injector.method;

import com.sheldon.elasticsearch.core.AbstractBaseObject;
import com.sheldon.elasticsearch.core.injector.AbstractMethod;

import java.util.concurrent.CompletableFuture;

public class Delete<T extends AbstractBaseObject> extends AbstractMethod<T> {
    @Override
    public CompletableFuture<?> invoke(Object... args) {
        return null;
    }
}
