package com.sheldon.elasticsearch.plus.core.annotation.field.parameter;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Fields {

    String name() default "";

    int ignore_above() default 256;
}
