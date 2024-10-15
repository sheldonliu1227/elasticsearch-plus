package com.sheldon.elasticsearch.plus.core.xcontent;

import cn.hutool.core.lang.ParameterizedTypeImpl;
import com.sheldon.elasticsearch.plus.core.annotation.field.ElasticSearchField;
import com.sheldon.elasticsearch.plus.core.toolkit.MetaInfoUtil;
import org.elasticsearch.xcontent.XContentBuilder;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FieldParser<A extends Annotation> extends BasicParser<A> {
    private final Field field;
    private final IParserFactory defaultParameterParserFactory = new DefaultParameterParserFactory<>();
    private final List<IParser> childrenParsers = new ArrayList<>();

    protected FieldParser(Field field) throws InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException, ClassNotFoundException {
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

    protected void initialized(A fieldAnnotation) throws InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException, ClassNotFoundException {
        if (null == fieldAnnotation) return;
        Method[] methods = fieldAnnotation.annotationType().getDeclaredMethods();
        for (Method method : methods) {
            if (method.getReturnType().isAnnotation() && notDefaultValue(fieldAnnotation, method.getName())) {
                IParser aiParser = defaultParameterParserFactory.create(method.getName(), method.invoke(fieldAnnotation));
                childrenParsers.add(aiParser);
            }
            if (method.getReturnType().isAssignableFrom(Class.class)) {
                Class<?> returnTypeClass = (Class<?>)method.invoke(fieldAnnotation);
                if (Arrays.asList(returnTypeClass.getInterfaces()).contains(IParser.class)) {
                    IParser aiParser = ((Class<? extends IParser>)returnTypeClass).newInstance();
                    childrenParsers.add(aiParser);
                } else if (IParser.class != returnTypeClass) {
                    Class<?> collectionType = Class.forName(((ParameterizedTypeImpl) field.getGenericType()).getActualTypeArguments()[0].getTypeName());
                    childrenParsers.add(new ClassParser(collectionType));
                } else {
                    childrenParsers.add(new ClassParser(field.getType()));
                }
            }

        }
    }


    @Override
    public XContentBuilder parse(XContentBuilder builder) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, IOException {
        ElasticSearchField meta = annotation.annotationType().getAnnotation(ElasticSearchField.class);
        builder.startObject(field.getName());
        builder.field(TYPE, meta.type());
        super.parse(builder);
        for (IParser notSimpleParser : childrenParsers) {
            notSimpleParser.parse(builder);
        }
        builder.endObject();
        return builder;
    }
}
