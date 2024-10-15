package com.sheldon.elasticsearch.plus.core.xcontent;

@FunctionalInterface
public interface IParameterParserFactory {
    IParser create(String parameterName, Object result);
}
