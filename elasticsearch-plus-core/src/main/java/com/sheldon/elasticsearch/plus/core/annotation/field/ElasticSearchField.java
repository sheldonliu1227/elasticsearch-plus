package com.sheldon.elasticsearch.plus.core.annotation.field;

import com.sheldon.elasticsearch.plus.core.constant.FieldTypeEnum;
import com.sun.istack.internal.NotNull;

import java.lang.annotation.*;

/**
 * 用于标记一个注解是es字段类型注解
 */
@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface ElasticSearchFieldType {
    Class<?>[] value();
}
