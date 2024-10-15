package com.sheldon.elasticsearch.plus.core.xcontent;

import org.elasticsearch.xcontent.XContentBuilder;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;

public class ParameterSimpleParser<A extends Annotation> extends BasicSimpleParser<A> {
    private final String parameterName;

    public ParameterSimpleParser(String parameterName, A annotation) {
        super();
        this.annotation = annotation;
        this.parameterName = parameterName;
    }

    @Override
    public XContentBuilder parse(XContentBuilder builder) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, IOException {
        builder.startObject(parameterName);
        super.parse(builder);
        builder.endObject();
        return builder;
    }
}