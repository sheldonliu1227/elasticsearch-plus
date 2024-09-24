package com.sheldon.elasticsearch.core.annotation;

import com.sheldon.elasticsearch.constant.FieldTypeEnum;

import java.lang.annotation.*;

/**
 * 用于描述一个索引的元数据信息
 */
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface NormalField {
    FieldTypeEnum fieldType() default FieldTypeEnum.KEYWORD;

    String fieldFormat() default "";
}
