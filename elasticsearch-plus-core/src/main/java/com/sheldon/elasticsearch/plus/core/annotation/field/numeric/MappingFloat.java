package com.sheldon.elasticsearch.plus.core.annotation.field.numeric;

import com.sheldon.elasticsearch.plus.core.annotation.field.ElasticSearchField;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@ElasticSearchField(
    type = "float" ,
    javaType = {java.lang.Float.class,  java.lang.Float[].class, float.class, float[].class}
)
public @interface Float {
}
