package com.sheldon.elasticsearch.core.query;

import com.sheldon.elasticsearch.core.AbstractBaseObject;
import org.elasticsearch.action.search.SearchRequestBuilder;

public abstract class QueryWrapper<T extends AbstractBaseObject> {
    private final Class<T> clazz;

    public QueryWrapper(Class<T> clazz) {
        this.clazz = clazz;
    }

    public abstract SearchRequestBuilder build();
}
