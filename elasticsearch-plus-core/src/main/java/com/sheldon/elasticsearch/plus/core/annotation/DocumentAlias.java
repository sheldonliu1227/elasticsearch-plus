package com.sheldon.elasticsearch.core.annotation;

import com.sheldon.elasticsearch.core.constant.RollOverTypeEnum;

import java.lang.annotation.*;

/**
 * 用于描述一个索引的元数据信息
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface DocumentAlias {
    /**
     * 索引别名
     */
    String indexAlias() default "";

    /**
     * 别名指向当前滚动索引后面提前几个索引
     */
    int preIndexCount() default 1;

    /**
     * 别名指向当前滚动索引之前几个索引
     */
    int postIndexCount() default 10;

    /**
     * 滚动类型
     */
    RollOverTypeEnum rollOverType() default RollOverTypeEnum.NULL;
}
