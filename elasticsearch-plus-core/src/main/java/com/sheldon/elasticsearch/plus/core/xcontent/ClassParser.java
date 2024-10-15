package com.sheldon.elasticsearch.plus.core.xcontent;

import com.sheldon.elasticsearch.plus.core.annotation.field.ElasticSearchField;
import com.sheldon.elasticsearch.plus.core.toolkit.MetaInfoUtil;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;


public class ClassSimpleParser {

    public ClassSimpleParser(Class<?> clazz) throws InvocationTargetException, IllegalAccessException {
        for (Field field : clazz.getDeclaredFields()) {
            if (isElasticSearchField(field)) {
                DefaultFieldParser<Annotation> annotationDefaultFieldParser = new DefaultFieldParser<>(field);
            }
        }
    }


    public Boolean isElasticSearchField(Field field) {
        for (Annotation annotation : field.getDeclaredAnnotations()) {
            if (MetaInfoUtil.isTargetOrMetaAnnotation(annotation, ElasticSearchField.class)) {
                return true;
            }
        }
        return false;
    }
}
