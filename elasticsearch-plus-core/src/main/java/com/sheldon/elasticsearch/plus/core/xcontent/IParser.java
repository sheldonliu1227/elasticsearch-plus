package com.sheldon.elasticsearch.plus.core.xcontent;

import org.elasticsearch.xcontent.XContentBuilder;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;


public interface IParser {

    XContentBuilder parse(XContentBuilder builder) throws NoSuchMethodException, IOException, InvocationTargetException, IllegalAccessException;
}
