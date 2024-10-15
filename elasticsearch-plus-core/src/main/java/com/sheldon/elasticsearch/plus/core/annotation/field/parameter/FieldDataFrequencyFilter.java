package com.sheldon.elasticsearch.plus.core.annotation.field.meta;

import java.lang.annotation.*;

/**
 * fielddata_frequency_filter 可以通过三个参数进行配置：
 *
 * 	1.	min：最小频率阈值。术语的出现频率必须大于此值，才会加载到 fielddata 中。可以是相对值（比例）或绝对值（次数）。
 * 	•	示例：min: 0.01 表示术语必须出现在 1% 的文档中才会加载。
 * 	2.	max：最大频率阈值。术语的出现频率必须小于此值，才会加载到 fielddata 中。可以是相对值或绝对值。
 * 	•	示例：max: 0.1 表示术语的出现频率不能超过 10%，否则将被忽略。
 * 	3.	min_segment_size：分段最小大小。只有当段中的文档数量大于此值时，min 和 max 过滤器才会被应用。这样可以避免对小段进行不必要的过滤操作。
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface FieldDataFrequencyFilter {
    double min();

    double max();

    int minSegmentSize();
}
