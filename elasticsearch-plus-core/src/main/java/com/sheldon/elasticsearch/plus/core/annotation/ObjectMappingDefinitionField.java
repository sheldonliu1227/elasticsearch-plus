package com.sheldon.elasticsearch.core.annotation;

import com.sheldon.elasticsearch.core.constant.FieldTypeEnum;
import com.sheldon.elasticsearch.core.mapping.DefaultMappingDefinitionPostProcessor;
import com.sheldon.elasticsearch.core.mapping.MappingDefinitionPostProcessor;

import java.lang.annotation.*;

@NormalField(fieldType = FieldTypeEnum.OBJECT)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface ObjectMappingDefinitionField {
    Class<? extends MappingDefinitionPostProcessor> processor() default DefaultMappingDefinitionPostProcessor.class;
}
