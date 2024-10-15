package com.sheldon.elasticsearch.plus.core.annotation.field;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@ElasticSearchField(type = "date")
public @interface MappingDate {
    double boost() default 1.0;

    boolean doc_values() default true;

    String format() default "strict_date_optional_time||epoch_millis";

    boolean ignore_malformed() default false;

    boolean index() default true;

    String null_value() default "";

    boolean store() default true;
}
