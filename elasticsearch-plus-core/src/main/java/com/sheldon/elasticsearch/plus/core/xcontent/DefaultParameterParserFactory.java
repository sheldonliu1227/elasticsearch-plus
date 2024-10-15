package com.sheldon.elasticsearch.plus.core.xcontent;

import java.lang.annotation.Annotation;

public class DefaultParameterParserFactory<A extends Annotation> implements IParserFactory {


    @Override
    public IParser create(String parameterName, Object result) {
        if (!Annotation.class.isAssignableFrom(result.getClass())) {
            throw new IllegalArgumentException("");
        }

        return new ParameterParser<>(parameterName, (A)result);
    }
}