package com.sheldon.elasticsearch.plus.core.annotation.field;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@ElasticSearchField(type = "nested")
public @interface MappingNested {
    boolean include_in_parent() default false;

    boolean include_in_root() default false;

    String dynamic() default "false";
}
