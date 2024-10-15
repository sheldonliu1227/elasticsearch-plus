package com.sheldon.elasticsearch.plus.core.annotation.field.range;

import com.sheldon.elasticsearch.plus.core.annotation.field.ElasticSearchField;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@ElasticSearchField(type = "date_range", javaType = {java.util.Date[].class})
public @interface DateRange {
    double boost() default 1.0;

    boolean doc_values() default true;

    String format() default "strict_date_optional_time||epoch_millis";

    boolean ignore_malformed() default false;

    boolean index() default true;

    String null_value() default "";

    boolean store() default true;

    String script() default "";

    String on_script_error() default "";
}
