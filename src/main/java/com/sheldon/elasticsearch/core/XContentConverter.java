package com.sheldon.elasticsearch.core;

import com.sheldon.elasticsearch.AbstractBaseObject;
import com.sheldon.elasticsearch.constant.FieldTypeEnum;
import com.sheldon.elasticsearch.constant.MappingEnum;
import com.sheldon.elasticsearch.core.annotation.Document;
import com.sheldon.elasticsearch.core.annotation.DynamicMappingDefinitionField;
import com.sheldon.elasticsearch.core.annotation.ClassMappingDefinitionField;
import com.sheldon.elasticsearch.core.annotation.NormalField;
import com.sheldon.elasticsearch.core.exception.ClassInaccuracyException;
import com.sheldon.elasticsearch.core.mapping.MappingDefinition;
import com.sheldon.elasticsearch.core.mapping.MappingDefinitionPostProcessor;
import com.sheldon.elasticsearch.core.mapping.MappingDefinitionPostProcessorFactory;
import org.elasticsearch.xcontent.XContentBuilder;
import org.elasticsearch.xcontent.XContentFactory;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;

public class XContentConverter {

    public static <T extends AbstractBaseObject> XContentBuilder object2Builder(T doc) throws IOException, ClassInaccuracyException, InstantiationException, IllegalAccessException {
        if (null == doc || !doc.getClass().isAnnotationPresent(Document.class)) {
            throw new IllegalArgumentException("the doc must be not null and must be annotated by @Document");
        }
        Document document = doc.getClass().getAnnotation(Document.class);
        XContentBuilder builder = XContentFactory.jsonBuilder().startObject();
        builder.field("dynamic", document.dynamicType().getCode());
        List<MappingDefinition> fieldMappingDefinitions = getFieldMappingDefinitions(doc.getClass());
        parseXContentBuilder(builder, fieldMappingDefinitions);
        builder.endObject();
        return builder;
    }


    private static <T extends AbstractBaseObject> void parseXContentBuilder(XContentBuilder builder, List<MappingDefinition> fieldMappingDefinitions) throws IOException, ClassInaccuracyException, InstantiationException, IllegalAccessException {
        builder.startObject("properties");
        for (MappingDefinition mappingDefinition : fieldMappingDefinitions) {
            parseXContentBuilder(builder, mappingDefinition);
        }
        builder.endObject();
    }

    private static <T extends AbstractBaseObject> void parseXContentBuilder(XContentBuilder builder, MappingDefinition fieldMappingDefinition) throws IOException, ClassInaccuracyException, InstantiationException, IllegalAccessException {
        builder.startObject(fieldMappingDefinition.getKey());
        FieldTypeEnum fieldType = fieldMappingDefinition.getMapping().getFieldType();
        builder.field("type", fieldType.getType());
        String format = fieldMappingDefinition.getFormat();
        if (FieldTypeEnum.DATE.equals(fieldType)) {
            if (null == format || format.isEmpty())
                throw new ClassInaccuracyException("the format of date field must be not null and not empty");
            builder.field("format", format);
        }
        if (!fieldMappingDefinition.getChildren().isEmpty()) {
            parseXContentBuilder(builder, fieldMappingDefinition.getChildren());
        }
        builder.endObject();
    }


    private static List<MappingDefinition> getFieldMappingDefinitions(Class<? extends AbstractBaseObject> clazz) throws ClassInaccuracyException, InstantiationException, IllegalAccessException {
        List<MappingDefinition> mappingDefinitions = new ArrayList<>();
        for (Field field : clazz.getDeclaredFields()) {
            mappingDefinitions.add(getFieldMappingDefinition(field));
        }
        return mappingDefinitions;
    }



    private static MappingDefinition getFieldMappingDefinition(Field field) throws ClassInaccuracyException, InstantiationException, IllegalAccessException {
        Annotation[] annotations = field.getDeclaredAnnotations();
        Annotation annotation = Arrays.stream(annotations).filter(a -> isTargetOrMetaAnnotation(a, NormalField.class)).findFirst().get();
        NormalField normalField = getNormalField(annotation);

        MappingEnum mappingEnum = MappingEnum.getMappingEnum(field.getType(), normalField.fieldType());
        if (null == mappingEnum) {
            throw new ClassInaccuracyException("the field type convert is not supported");
        }
        if (NormalField.class == annotation.annotationType()) {
            return new MappingDefinition(
                    field.getName(),
                    mappingEnum,
                    ((NormalField) annotation).fieldFormat()
            );
        }
        NormalField parentField = annotation.annotationType().getAnnotation(NormalField.class);
        if (parentField == null) {
            throw new ClassInaccuracyException("the field type is not invalid");
        }
        MappingDefinition mappingDefinition = new MappingDefinition(
                field.getName(),
                mappingEnum,
                null);
        if (FieldTypeEnum.NESTED.equals(parentField.fieldType())) {
            ClassMappingDefinitionField classMappingDefinitionField = (ClassMappingDefinitionField) annotation;
            Field[] childrenFields = classMappingDefinitionField.nestedClass().getDeclaredFields();
            for (Field childrenField : childrenFields) {
                mappingDefinition.addChild(getFieldMappingDefinition(childrenField));
            }
            if (mappingDefinition.isNotReady()) {
                throw new ClassInaccuracyException("the nested class is not ready");
            }
        }
        if (FieldTypeEnum.OBJECT.equals(parentField.fieldType())) {
            DynamicMappingDefinitionField dynamicMappingDefinitionField = (DynamicMappingDefinitionField) annotation;
            MappingDefinitionPostProcessor mappingDefinitionPostProcessor = MappingDefinitionPostProcessorFactory.getMappingDefinitionPostProcessor(dynamicMappingDefinitionField.processor());
            mappingDefinition.addAll(mappingDefinitionPostProcessor.process());
            if (mappingDefinition.isNotReady()) {
                throw new ClassInaccuracyException("the object class is not ready");
            }
        }
        return mappingDefinition;
    }

    // 判断是否是目标注解或它的元注解
    private static boolean isTargetOrMetaAnnotation(Annotation annotation, Class<? extends Annotation> targetAnnotation) {
        // 检查注解本身是否是目标注解
        if (annotation.annotationType().equals(targetAnnotation)) {
            return true;
        }

        // 检查注解是否有元注解，并且元注解是目标注解
        for (Annotation metaAnnotation : annotation.annotationType().getAnnotations()) {
            if (metaAnnotation.annotationType().equals(targetAnnotation)) {
                return true;
            }
        }
        return false;
    }

    private static NormalField getNormalField(Annotation annotation) throws ClassInaccuracyException {
        // 检查注解本身是否是目标注解
        if (annotation.annotationType().equals(NormalField.class)) {
            return (NormalField)annotation;
        }

        // 检查注解是否有元注解，并且元注解是目标注解
        for (Annotation metaAnnotation : annotation.annotationType().getAnnotations()) {
            if (metaAnnotation.annotationType().equals(NormalField.class)) {
                return (NormalField)metaAnnotation;
            }
        }
        throw new ClassInaccuracyException("annotation is not NormalField or its meta-annotation");
    }
}
