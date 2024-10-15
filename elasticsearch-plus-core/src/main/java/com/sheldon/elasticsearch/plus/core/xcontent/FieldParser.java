package com.sheldon.elasticsearch.plus.core.xcontent;

import com.sheldon.elasticsearch.plus.core.annotation.field.ElasticSearchField;
import com.sheldon.elasticsearch.plus.core.toolkit.MetaInfoUtil;
import org.elasticsearch.xcontent.XContentBuilder;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class DefaultFieldParser<A extends Annotation> extends BasicSimpleParser<A> {
    private final List<IParser> notSimpleParsers = new ArrayList<>();
    private final Field field;
    private final IParameterParserFactory<A> defaultParameterParserFactory = new DefaultParameterParserFactory<>();

    protected DefaultFieldParser(Field field) throws InvocationTargetException, IllegalAccessException {
        this.field = field;
        this.annotation = getFieldAnnotation();
        initialized(annotation);
    }


    protected A getFieldAnnotation() {
        Annotation[] annotations = field.getDeclaredAnnotations();
        for (Annotation annotation : annotations) {
            if (MetaInfoUtil.isTargetOrMetaAnnotation(annotation, ElasticSearchField.class)) {
                return (A) annotation;
            }
        }
        return null;
    }

    protected void initialized(A fieldAnnotation) throws InvocationTargetException, IllegalAccessException {
        if (null == fieldAnnotation) return;
        Method[] methods = fieldAnnotation.getClass().getDeclaredMethods();
        for (Method method : methods) {
            Class<?> returnType = method.getReturnType();
            if (!Annotation.class.isAssignableFrom(returnType)) {
                continue;
            }
            IParser aiParser = defaultParameterParserFactory.create(method.getName(), (A) method.invoke(fieldAnnotation));
            notSimpleParsers.add(aiParser);
        }
    }


    @Override
    public XContentBuilder parse(XContentBuilder builder) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, IOException {
        ElasticSearchField meta = annotation.annotationType().getAnnotation(ElasticSearchField.class);
        builder.startObject(field.getName());
        builder.field("type", meta.type());
        super.parse(builder);
        for (IParser notSimpleParser : notSimpleParsers) {
            notSimpleParser.parse(builder);
        }
        builder.endObject();
        return builder;
    }
}
