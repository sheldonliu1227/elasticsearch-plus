package com.sheldon.elasticsearch.plus.core.annotation.field;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@ElasticSearchField(type = "ip", javaType = String.class)
public @interface Ip {
    double boost() default 1.0;

    boolean doc_values() default true;

    boolean ignore_malformed() default false;

    boolean index() default true;

    String null_value() default "";

    boolean store() default true;

    boolean time_series_dimension() default false;
}
