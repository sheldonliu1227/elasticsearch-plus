package com.sheldon.elasticsearch.plus.core.annotation.field.numeric;

import com.sheldon.elasticsearch.plus.core.annotation.field.ElasticSearchField;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@ElasticSearchField(
    type = "integer" ,
    javaType = {java.lang.Integer.class,  java.lang.Integer[].class, int.class, int[].class}
)
public @interface Integer {
}
