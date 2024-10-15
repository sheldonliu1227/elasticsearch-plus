package com.sheldon.elasticsearch.plus.core.xcontent;

import org.elasticsearch.xcontent.XContentBuilder;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;

public class ParameterParser<A extends Annotation> extends BasicParser<A> {
    private final String parameterName;

    public ParameterParser(String parameterName, A annotation) {
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