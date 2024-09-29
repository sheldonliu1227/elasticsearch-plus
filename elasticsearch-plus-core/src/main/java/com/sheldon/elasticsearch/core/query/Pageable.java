package com.sheldon.elasticsearch.core.query;


import org.elasticsearch.search.aggregations.Aggregation;

import java.util.List;
import java.util.Map;


public class PageableData<T> {
    // 当前页码
    private int pageNum;
    // 单页大小
    private int pageSize;
    // 总条数
    private int total;
    // 总页数
    private int pages;
    // 是否还有更多数据
    private boolean more;
    // 命中数据
    private List<T> hits;
    // 聚合扁平化集合
    private Map<String, Aggregation> flatAggregations;
}
