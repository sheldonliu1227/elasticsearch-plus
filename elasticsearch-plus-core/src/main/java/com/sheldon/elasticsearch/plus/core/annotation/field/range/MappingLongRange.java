package com.sheldon.elasticsearch.plus.core.annotation.field.range;

import com.sheldon.elasticsearch.plus.core.annotation.field.ElasticSearchField;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@ElasticSearchField(type = "long_range", javaType = {Long[].class, long.class})
public @interface MappingLongRange {
}
