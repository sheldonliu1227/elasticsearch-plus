package com.sheldon.elasticsearch.plus.core.xcontent;

@FunctionalInterface
public interface IParserFactory {
    IParser create(String parameterName, Object result);
}
