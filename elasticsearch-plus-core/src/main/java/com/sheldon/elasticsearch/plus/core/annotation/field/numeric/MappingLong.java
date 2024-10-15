package com.sheldon.elasticsearch.plus.core.annotation.field.numeric;

import com.sheldon.elasticsearch.plus.core.annotation.field.ElasticSearchField;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@ElasticSearchField(
    type = "long" ,
    javaType = {java.lang.Long.class,  java.lang.Long[].class, long.class, long[].class}
)
public @interface Long {
}
