package com.sheldon.elasticsearch.core.annotation;

import com.sheldon.elasticsearch.constant.FieldTypeEnum;

import java.lang.annotation.*;


@NormalField(fieldType = FieldTypeEnum.NESTED)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface ClassMappingDefinitionField {
    Class<?> nestedClass();
}
