package com.sheldon.elasticsearch.plus.core.annotation.field;

import com.sheldon.elasticsearch.plus.core.xcontent.IParser;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@ElasticSearchField(type = "object")
public @interface MappingObject {
    String dynamic() default "false";

    boolean enabled() default true;

    Class<? extends IParser> child() default IParser.class;
}
