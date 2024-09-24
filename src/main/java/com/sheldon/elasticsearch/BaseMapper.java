package com.sheldon.elasticsearch;

import java.util.concurrent.Future;

public interface BaseMapper<T extends AbstractBaseObject> {
    /**
     * merge index of current partition
     * @param doc
     * @return success count
     */
    int mergeIndex(T doc);

    /**
     * merge all indies of alias
     * @param doc
     * @return success count
     */
    int mergeAlias(T doc);
    /**
     * save document to the last index
     *
     * @param doc document to insert
     * @return success count
     */
    int insert(T doc);

    /**
     * save document to the last index
     *
     * @param doc document to insert
     */
    Future<Integer> insertAsync(T doc);

    int update(T doc);
}
