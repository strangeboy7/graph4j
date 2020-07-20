package com.github.strangeboy7.graph4j;

import java.util.List;

/**
 * search order
 * @param <T>
 */
interface ISearchOrder<T> {
    /**
     * pre order
     * @return
     */
    List<T> pre();

    /**
     * post order
     * @return
     */
    List<T> post();

    /**
     * reverse post order
     * @return
     */
    List<T> reversePost();
}
