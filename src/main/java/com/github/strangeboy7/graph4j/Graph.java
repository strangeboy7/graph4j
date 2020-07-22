package com.github.strangeboy7.graph4j;

import java.util.Collections;
import java.util.TreeSet;

/**
 * Undirected graph
 *
 * @param <T>
 * @see Digraph
 */
public class Graph<T> extends CommonGraph<T> implements IGraph<T> {

    @Override
    public void addEdge(T t1, T t2) {
        if (t1 == null || t2 == null) return;
        adj.putIfAbsent(t1, Collections.synchronizedSet(new TreeSet<>()));
        adj.putIfAbsent(t2, Collections.synchronizedSet(new TreeSet<>()));
        adj.get(t1).add(t2);
        adj.get(t2).add(t1);
    }


    @Override
    public void removeEdge(T from, T to) {
        if (from == null || to == null) return;
        adj.getOrDefault(from, Collections.emptySet()).remove(to);
        adj.getOrDefault(to, Collections.emptySet()).remove(from);
    }


}
