package com.sheldon.elasticsearch.core.query;


import org.elasticsearch.search.aggregations.Aggregation;

import java.util.List;
import java.util.Map;


public class Pageable<T> {
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

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public boolean isMore() {
        return more;
    }

    public void setMore(boolean more) {
        this.more = more;
    }

    public List<T> getHits() {
        return hits;
    }

    public void setHits(List<T> hits) {
        this.hits = hits;
    }

    public Map<String, Aggregation> getFlatAggregations() {
        return flatAggregations;
    }

    public void setFlatAggregations(Map<String, Aggregation> flatAggregations) {
        this.flatAggregations = flatAggregations;
    }
}
