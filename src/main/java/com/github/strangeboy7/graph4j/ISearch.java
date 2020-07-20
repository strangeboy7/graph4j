package com.github.strangeboy7.graph4j;

import java.util.List;

/**
 * graph search interface
 * @param <T> graphic vertex type
 */
interface ISearch<T> {

    /**
     * is the vertex in the search path
     * @param t vertex
     * @return is the vertex in the search path
     */
    boolean marked(T t);

    /**
     * search path to the vertex
     * @param t vertex
     * @return
     */
    List<T> pathTo(T t);

    /**
     * how many vertex reached during the search
     * @return
     */
    int count();
}
