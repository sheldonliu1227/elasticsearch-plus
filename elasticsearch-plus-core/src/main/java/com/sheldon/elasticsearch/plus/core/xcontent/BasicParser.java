package com.sheldon.elasticsearch.plus.core.xcontent;

import org.elasticsearch.xcontent.XContentBuilder;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;


public class BasicSimpleParser<A extends Annotation> implements IParser {
    protected A annotation;

    @Override
    public XContentBuilder parse(XContentBuilder builder) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, IOException {
        Method[] methods = annotation.getClass().getDeclaredMethods();
        for (Method method : methods) {
            if (notDefaultValue(annotation, method.getName()) && !method.getReturnType().isAnnotation()) {
                builder.field(method.getName(), method.invoke(annotation));
            }
        }
        return builder;
    }

    /**
     * 判断注解的某个属性是否为默认值
     */
    protected boolean notDefaultValue(A annotation, String methodName) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = annotation.getClass().getMethod(methodName);
        Object value = method.invoke(annotation);
        Object defaultValue = method.getDefaultValue();
        return Objects.deepEquals(value, defaultValue);
    }
}
