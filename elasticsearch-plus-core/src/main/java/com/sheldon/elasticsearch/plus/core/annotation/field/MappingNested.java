package com.sheldon.elasticsearch.plus.core.annotation.field;

import java.lang.annotation.*;
import java.lang.reflect.Array;
import java.util.Collection;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@ElasticSearchField(type = "nested" , javaType = {Collection.class, Array.class})
public @interface Nested {

    String dynamic() default "true";

    boolean include_in_parent() default false;

    boolean include_in_root() default false;
}
