package com.sheldon.elasticsearch.plus.core.annotation.field;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@ElasticSearchField(type = "point")
public @interface MappingPoint {
    boolean ignore_malformed() default false;

    boolean ignore_z_value() default true;

    String null_value() default "";
}
