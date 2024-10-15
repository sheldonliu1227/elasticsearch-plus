package com.sheldon.elasticsearch.plus.core.xcontent;

import com.sheldon.elasticsearch.plus.core.constant.ConstantsMapping;
import org.elasticsearch.xcontent.XContentBuilder;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;


public class BasicParser<A extends Annotation> implements IParser, ConstantsMapping {
    protected A annotation;

    @Override
    public XContentBuilder parse(XContentBuilder builder) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, IOException {
        Method[] methods = annotation.annotationType().getDeclaredMethods();
        for (Method method : methods) {
            if (isBasicType(method.getReturnType()) && notDefaultValue(annotation, method.getName())) {
                builder.field(method.getName(), method.invoke(annotation));
            }
        }
        return builder;
    }

    /**
     * 判断注解的某个属性不是默认值
     */
    protected boolean notDefaultValue(A annotation, String methodName) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = annotation.annotationType().getMethod(methodName);
        Object value = method.invoke(annotation);
        Object defaultValue = method.getDefaultValue();
        return !Objects.deepEquals(value, defaultValue);
    }

    /**
     * 判断某个类是Class范型或者接口或者注解
     */
    protected boolean isBasicType(Class<?> clazz) {
        return !clazz.isAssignableFrom(Class.class) && !clazz.isInterface() && !clazz.isAnnotation();
    }
}
