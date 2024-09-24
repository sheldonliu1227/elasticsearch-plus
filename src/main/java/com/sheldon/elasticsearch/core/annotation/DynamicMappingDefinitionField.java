package com.sheldon.elasticsearch.core.annotation;

import com.sheldon.elasticsearch.constant.FieldTypeEnum;
import com.sheldon.elasticsearch.core.mapping.MappingDefinitionPostProcessor;

import java.lang.annotation.*;

@NormalField(fieldType = FieldTypeEnum.OBJECT)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface DynamicMappingDefinitionField {
    Class<? extends MappingDefinitionPostProcessor> processor();
}
