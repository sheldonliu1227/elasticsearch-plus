package com.sheldon.elasticsearch.plus.core.annotation.field.parameter;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Meta {

    /**
     * The unit associated with a numeric field: "percent", "byte" or a time unit. By default, a field does not have a unit. Only valid for numeric fields. The convention for percents is to use value 1 to mean 100%.
     */
    String unit() default "";

    /**
     * The type of the metric: "gauge", "counter", "histogram", "summary" or "set". By default, a field does not have a metric type.
     */
    String metricType() default "";
}
