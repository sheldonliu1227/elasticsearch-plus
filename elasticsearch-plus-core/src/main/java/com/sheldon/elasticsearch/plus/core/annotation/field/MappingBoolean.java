package com.sheldon.elasticsearch.plus.core.annotation.field;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@ElasticSearchField(
        type = "boolean",
        javaType = {java.lang.Boolean.class, java.lang.Boolean[].class}
)
public @interface Boolean {
    double boost() default 1.0;

    boolean doc_values() default true;

    boolean index() default true;

    String null_value() default "";

    boolean store() default true;
}
