package com.github.strangeboy7.graph4j;

import java.util.List;
import java.util.Set;

public interface IGraph<T> {
    void addEdge(T t1, T t2);

    Set<T> vertex();

    Set<T> adj(T t);

    int edge();

    int degree(T t);

    int maxDegree();

    Set<T> getConnected(T t);

    boolean connected(T t1, T t2);

    List<T> pathTo(T from, T to);
}
