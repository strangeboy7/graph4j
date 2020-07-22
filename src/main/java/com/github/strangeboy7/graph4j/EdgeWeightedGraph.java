package com.github.strangeboy7.graph4j;

import java.util.*;

public class EdgeWeightedGraph<T> {
    private Map<T, Set<Edge<T>>> adj = new HashMap<>();

    private int edgeCount;

    public int edgeCount() {
        return edgeCount;
    }

    public int vertexCount() {
        return adj.keySet().size();
    }

    public void addEdge(Edge<T> e) {
        Objects.requireNonNull(e);

        T v = e.either();
        T w = e.other(v);
        adj.putIfAbsent(v, new TreeSet<>());
        adj.putIfAbsent(w, new TreeSet<>());
        adj.get(v).add(e);
        adj.get(w).add(e);
        edgeCount++;
    }

    public Set<T> vertex() {
        return new TreeSet<>(adj.keySet());
    }

    public Set<Edge<T>> adj(T v) {
        return new HashSet<>(adj.getOrDefault(v, Collections.emptySet()));
    }

    public Set<Edge<T>> edges() {
        Set<Edge<T>> rs = new HashSet<>();
        adj.values().forEach(rs::addAll);
        return rs;
    }
}
