package com.sheldon.elasticsearch.plus.core.annotation.field.numeric;

import com.sheldon.elasticsearch.plus.core.annotation.field.ElasticSearchField;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@ElasticSearchField(
    type = "double" ,
    javaType = {java.lang.Double.class,  java.lang.Double[].class, double.class, double[].class}
)
public @interface Double {
}
