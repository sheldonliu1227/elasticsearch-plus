package com.sheldon.elasticsearch.plus.core.annotation.field;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@ElasticSearchField(type = "keyword")
public @interface MappingKeyWord {
    /**
     * 是否需要存储该字段
     */
    boolean docValues() default true;


    /**
     * eager_global_ordinals 属性允许在索引合并（segment merging）时立即构建 global ordinals，而不是在查询时按需生成。启用此选项可以减少首次查询时的延迟（因为索引会在后台生成 global ordinals），尤其是在具有大量唯一值的字段上。
     * 使用场景:
     * 高频聚合或排序：如果你在一个字段上经常进行聚合、排序或者 terms 查询，提前加载 global ordinals 可以提高首次查询的响应速度。
     * 查询高延迟：对于有很多唯一值的字段，按需生成 global ordinals 可能会引入显著的延迟。开启 eager_global_ordinals 可以在索引时预先计算，避免查询时的性能瓶颈。
     */
    boolean eagerGlobalOrdinals() default false;

    /**
     * text 类型字段可以有子字段。你可以定义一个 keyword 子字段，以便对同一个字段既可以进行全文搜索，也可以进行精确匹配。
     */
    String fields() default "";

    /**
     * ignore_above 属性用于指定一个长度阈值，当字段值长度超过这个阈值时，Elasticsearch 将忽略该字段值，不再对其进行索引。这个属性通常用于防止长文本字段占用过多的索引空间。
     */
    int ignoreAbove() default 256;

    /**
     * 控制字段是否被索引，true（默认）表示可以搜索，false表示不可以。
     */
    boolean index() default true;

    /**
     * docs：当你只关心某个词项是否出现在文档中，而不关心词频、位置等信息时（例如简单的过滤条件查询），可以选择 docs 来节省存储空间。
     * freqs：如果你需要词频来提高相关性计算精度，但不需要短语匹配或位置相关查询，可以选择 freqs，这会保留词频信息以便提高搜索结果的相关性。
     * positions：这是默认配置，适用于大多数全文检索场景，特别是当你需要支持短语查询或临近词查询时。
     * offsets：当你需要在搜索结果中高亮显示匹配的词项，并且希望显示具体的词项在文本中的位置时，应该选择 offsets。
     */
    String indexOptions() default "positions";

    /**
     * 当进行全文检索时，norms 可以帮助提升短文档的相关性。默认情况下，较短的文档中包含的词项被认为比较长的文档中包含的词项更有意义。例如，在一个短文档中某个词项出现的次数较少，但这个词项的权重可能比在较长文档中频繁出现的相同词项更高。
     */
    boolean norms() default true;

    /**
     * 默认情况下，Elasticsearch 会将 null 值视为一个特殊的词项，并将其存储在索引中。然而，在某些情况下，你可能希望将 null 值视为一个缺失的字段，而不是一个特殊的词项。在这种情况下，你可以使用 null_value 属性来指定一个值，当字段值为 null 时，Elasticsearch 会将该值存储在索引中
     */
    String nullValue() default "";

    /**
     * 是否单独存储字段值，默认是false。
     */
    boolean store() default false;

    /**
     * 指定查询时使用的相似度算法，例如 BM25、DFR、IB、OKAPI_BM25 等。
     */
    String similarity() default "BM25";
}
