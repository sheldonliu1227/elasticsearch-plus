package com.sheldon.elasticsearch.plus.core.xcontent;

import com.sheldon.elasticsearch.plus.core.annotation.field.ElasticSearchField;
import com.sheldon.elasticsearch.plus.core.constant.ConstantsMapping;
import com.sheldon.elasticsearch.plus.core.toolkit.MetaInfoUtil;
import org.elasticsearch.xcontent.XContentBuilder;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;


public class ClassParser implements IParser, ConstantsMapping {
    private final List<FieldParser<? extends Annotation>> fieldParsers = new ArrayList<>();

    public ClassParser(Class<?> clazz) throws InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException, ClassNotFoundException {
        for (Field field : clazz.getDeclaredFields()) {
            if (isElasticSearchField(field)) {
                fieldParsers.add(new FieldParser<>(field));
            }
        }
    }

    private Boolean isElasticSearchField(Field field) {
        for (Annotation annotation : field.getDeclaredAnnotations()) {
            if (MetaInfoUtil.isTargetOrMetaAnnotation(annotation, ElasticSearchField.class)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public XContentBuilder parse(XContentBuilder builder) throws NoSuchMethodException, IOException, InvocationTargetException, IllegalAccessException {
        builder.startObject(PROPERTIES);
        for (FieldParser<? extends Annotation> fieldParser : fieldParsers) {
            fieldParser.parse(builder);
        }
        builder.endObject();
        return builder;
    }
}
