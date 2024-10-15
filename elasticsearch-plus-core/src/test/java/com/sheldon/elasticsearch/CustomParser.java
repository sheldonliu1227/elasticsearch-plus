package com.sheldon.elasticsearch;

import com.sheldon.elasticsearch.plus.core.xcontent.IParser;
import org.elasticsearch.xcontent.XContentBuilder;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class CustomParser implements IParser {
    @Override
    public XContentBuilder parse(XContentBuilder builder) throws NoSuchMethodException, IOException, InvocationTargetException, IllegalAccessException {
        builder.startObject("properties");
        builder.startObject("aaa");
        builder.field("type", "integer");

        builder.endObject();
        builder.endObject();
        return builder;
    }
}
