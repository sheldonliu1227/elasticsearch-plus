package com.sheldon.elasticsearch.plus.core.annotation.field;

import java.lang.annotation.*;

@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface ElasticSearchField {
    String type();
}
