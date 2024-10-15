package com.sheldon.elasticsearch.plus.core.annotation.field;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@ElasticSearchField(type = "object", javaType = {java.lang.Object.class, java.lang.Object[].class})
public @interface Object {

    String dynamic() default "true";

    boolean enabled() default true;
}
