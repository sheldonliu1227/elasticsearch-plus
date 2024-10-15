package com.sheldon.elasticsearch.plus.core.annotation.field;


import com.sheldon.elasticsearch.plus.core.annotation.field.parameter.FieldDataFrequencyFilter;
import com.sheldon.elasticsearch.plus.core.annotation.field.parameter.Fields;
import com.sheldon.elasticsearch.plus.core.annotation.field.parameter.IndexPrefixes;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@ElasticSearchField(type = "text")
public @interface MappingText {
    /**
     * 指定文本如何被分词，例如standard、whitespace等。
     */
    String analyzer() default "standard";

    /**
     * 用于控制字段在查询时的权重，默认为 1.0。权重较高的字段在相关性计算中会得到更高的分数。
     */
    double boost() default 1.0;


    /**
     * eager_global_ordinals 属性允许在索引合并（segment merging）时立即构建 global ordinals，而不是在查询时按需生成。启用此选项可以减少首次查询时的延迟（因为索引会在后台生成 global ordinals），尤其是在具有大量唯一值的字段上。
     * 使用场景:
     * 高频聚合或排序：如果你在一个字段上经常进行聚合、排序或者 terms 查询，提前加载 global ordinals 可以提高首次查询的响应速度。
     * 查询高延迟：对于有很多唯一值的字段，按需生成 global ordinals 可能会引入显著的延迟。开启 eager_global_ordinals 可以在索引时预先计算，避免查询时的性能瓶颈。
     */
    boolean eager_global_ordinals() default false;

    /**
     * 当试图对 text 字段进行排序、聚合或进行 terms 查询时，Elasticsearch 需要将分词后的数据结构加载到内存中，而 fielddata 就是为这种情况设计的。默认情况下，fielddata 是禁用的，因为加载整个字段到内存中可能会耗费大量内存资源。
     * 使用场景:
     * 通常，我们不会在 text 字段上执行排序或聚合操作，而会使用 keyword 字段来处理这些需求。如果你确实需要在 text 字段上进行这些操作，才需要启用 fielddata。
     */
    boolean fielddata() default false;

    /**
     * fielddata_frequency_filter 是 Elasticsearch 中的一种优化设置，用于减少加载到内存的 fielddata 中的术语（terms）的数量。它主要用于 text 类型字段在启用 fielddata 时，帮助过滤掉频率极高或极低的术语，以节省内存并提升性能。
     * fielddata_frequency_filter 允许你根据术语出现的频率（frequency）来过滤掉不需要的数据，比如那些非常常见或者非常罕见的术语，从而优化内存使用。
     */
    FieldDataFrequencyFilter fielddata_frequency_filter() default @FieldDataFrequencyFilter(min = 0.01, max = 0.1, minSegmentSize = 1000);


    /**
     * text 类型字段可以有子字段。你可以定义一个 keyword 子字段，以便对同一个字段既可以进行全文搜索，也可以进行精确匹配。
     */
    Fields fields() default @Fields;

    /**
     * 控制字段是否被索引，true（默认）表示可以搜索，false表示不可以。
     */
    boolean index() default true;

    /**
     * 	docs：当你只关心某个词项是否出现在文档中，而不关心词频、位置等信息时（例如简单的过滤条件查询），可以选择 docs 来节省存储空间。
     * 	freqs：如果你需要词频来提高相关性计算精度，但不需要短语匹配或位置相关查询，可以选择 freqs，这会保留词频信息以便提高搜索结果的相关性。
     * 	positions：这是默认配置，适用于大多数全文检索场景，特别是当你需要支持短语查询或临近词查询时。
     * 	offsets：当你需要在搜索结果中高亮显示匹配的词项，并且希望显示具体的词项在文本中的位置时，应该选择 offsets。
     */
    String index_options() default "positions";

    /**
     * 通常情况下，Elasticsearch 对 text 字段进行分词后，词项被存储在倒排索引中。但对于前缀查询，默认情况下搜索引擎需要通过扫描大量词项来查找匹配的前缀，这会降低查询性能。通过启用 index_prefixes，Elasticsearch 能够预先为这些前缀建立索引，从而显著提升前缀查询的性能。
     */
    IndexPrefixes index_prefixes() default @IndexPrefixes;

    /**
     * 用于加速短语查询（phrase query）的性能。短语查询是指要求查询的词项按特定顺序、在指定的间距内出现的查询类型。通常情况下，短语查询通过遍历倒排索引中的词项来匹配特定的词序和词间距，这个过程可能会相对耗时。如果启用了 index_phrases，Elasticsearch 会为短语创建额外的索引，从而提升短语查询的执行速度。
     */
    boolean index_phrases() default false;

    /**
     * 当进行全文检索时，norms 可以帮助提升短文档的相关性。默认情况下，较短的文档中包含的词项被认为比较长的文档中包含的词项更有意义。例如，在一个短文档中某个词项出现的次数较少，但这个词项的权重可能比在较长文档中频繁出现的相同词项更高。
     */
    boolean norms() default true;

    /**
     * position_increment_gap 是一个用于控制词项位置增量间隔的参数。在 Elasticsearch 中，文本字段会被分词，每个词项的位置信息会被记录下来。当多个词项在同一个位置出现时，Elasticsearch 会将它们视为同一个词项，并只记录一个位置。position_increment_gap 参数用于设置位置增量间隔，即当两个词项之间的位置差距
     */
    double position_increment_gap() default 100;

    /**
     * 是否单独存储字段值，默认是false。
     */
    boolean store() default false;

    /**
     * 指定查询时使用的分词器。
     */
    String search_analyzer() default "";

    /**
     * 指定引号内容查询时使用的分词器。
     */
    String search_quote_analyzer() default "";

    /**
     * 指定查询时使用的相似度算法，例如 BM25、DFR、IB、OKAPI_BM25 等。
     */
    String similarity() default "BM25";

    /**
     * no: No term vectors are stored. (default)
     * yes: Just the terms in the field are stored.
     * with_positions: Terms and positions are stored.
     * with_offsets: Terms and character offsets are stored.
     * with_positions_offsets: Terms, positions, and character offsets are stored.
     * with_positions_payloads: Terms, positions, and payloads are stored.
     * with_positions_offsets_payloads: Terms, positions, offsets and payloads are stored.
     */
    String term_vector() default "no";
}
