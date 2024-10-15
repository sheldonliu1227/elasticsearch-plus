package com.sheldon.elasticsearch.plus.core.annotation.field;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@ElasticSearchField(type = "geo_shape")
public @interface MappingGeoShape {

    String tree() default "quadtree";

    String precision() default "1m";

    String tree_levels() default "various";

    String strategy() default "recursive";

    double distance_error_pct() default 0.025;

    String orientation() default "RIGHT";

    boolean points_only() default false;

    boolean ignore_malformed() default false;

    boolean ignore_z_value() default true;

    boolean coerce() default false;
}
