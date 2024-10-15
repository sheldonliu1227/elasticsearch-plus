package com.sheldon.elasticsearch.plus.core.xcontent;

import java.lang.annotation.Annotation;

public class DefaultParameterParserFactory<A extends Annotation> implements IParameterParserFactory<A> {
    @Override
    public ParameterParser<A> create(String name, A annotation) {
        return new ParameterParser<>(name, annotation);
    }
}