package com.sheldon.elasticsearch.plus.core.annotation.field.numeric;

import com.sheldon.elasticsearch.plus.core.annotation.field.ElasticSearchField;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@ElasticSearchField(
    type = "short" ,
    javaType = {java.lang.Short.class,  java.lang.Short[].class, short.class, short[].class}
)
public @interface Short {
}
