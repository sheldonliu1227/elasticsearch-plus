package com.sheldon.elasticsearch.plus.core.annotation.field.parameter;

import java.lang.annotation.*;

/**
 * 	min_chars：前缀索引的最小字符数。默认值为 2，表示至少两个字符组成的前缀会被索引。
 * 	max_chars：前缀索引的最大字符数。默认值为 5，表示最多五个字符长度的前缀会被索引。
 *
 * 通过这些配置，可以控制前缀索引的范围，既提高前缀查询的速度，又避免为过长的前缀建立不必要的索引。
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface IndexPrefixes {
    int minChars() default 2;

    int maxChars() default 5;
}
