package com.sheldon.elasticsearch.plus.core.annotation.field.range;

import com.sheldon.elasticsearch.plus.core.annotation.field.ElasticSearchField;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@ElasticSearchField(type = "integer_range", javaType = { Integer[].class, int.class})
public @interface MappingIntegerRange {
}
