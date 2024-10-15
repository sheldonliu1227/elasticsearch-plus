package com.sheldon.elasticsearch.plus.core.annotation.field;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@ElasticSearchField(type = "geo_point", javaType = String.class)
public @interface GeoPoint {

    boolean ignore_malformed() default false;

    boolean ignore_z_value() default true;

    boolean index() default true;

    String null_value() default "";
}
