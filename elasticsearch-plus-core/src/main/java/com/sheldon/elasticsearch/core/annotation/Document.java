package com.sheldon.elasticsearch.core.annotation;

import com.sheldon.elasticsearch.core.constant.DynamicTypeEnum;

import java.lang.annotation.*;

/**
 * 用于描述一个索引的元数据信息
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Document {
    /**
     * 索引名称
     */
    String indexName();

    /**
     * 索引分片数
     */
    int shards() default 10;

    /**
     * 索引副本数
     */
    int replicas() default 1;

    /**
     * 索引是否动态创建
     */
    DynamicTypeEnum dynamicType() default DynamicTypeEnum.FALSE;

    /**
     * 别名信息
     */
    Alias alias() default @Alias;
}
