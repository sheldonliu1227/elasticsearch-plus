package com.sheldon.elasticsearch.plus.core.annotation.field.range;

import com.sheldon.elasticsearch.plus.core.annotation.field.ElasticSearchField;
import org.elasticsearch.search.aggregations.bucket.range.IpRangeAggregationBuilder;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@ElasticSearchField(type = "float_range", javaType = {IpRangeAggregationBuilder.Range[].class, float.class})
public @interface MappingFloatRange {
}
