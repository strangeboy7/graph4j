package com.github.strangeboy7.graph4j;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * Undirected graph
 * @see Digraph
 * @param <T>
 */
public class Graph<T> extends CommonGraph<T> implements IGraph<T> {

    @Override
    public void addEdge(T t1, T t2) {
        adj.putIfAbsent(t1, Collections.synchronizedSet(new TreeSet<>()));
        adj.putIfAbsent(t2, Collections.synchronizedSet(new TreeSet<>()));
        adj.get(t1).add(t2);
        adj.get(t2).add(t1);
    }



}
