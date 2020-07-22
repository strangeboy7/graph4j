package com.github.strangeboy7.graph4j;

import java.util.*;

public class EdgeWeightedDigraph<T> {
    private Map<T, Set<DirectedEdge<T>>> adj = new HashMap<>();

    private int edgeCount;

    public int edgeCount() {
        return edgeCount;
    }

    public int vertexCount() {
        return adj.keySet().size();
    }

    public void addEdge(DirectedEdge<T> e) {
        Objects.requireNonNull(e);

        T v = e.from();
        T w = e.to();
        adj.putIfAbsent(v, new TreeSet<>());
        adj.putIfAbsent(w, new TreeSet<>());
        adj.get(v).add(e);
        edgeCount++;
    }

    public Set<T> vertex() {
        return new TreeSet<>(adj.keySet());
    }

    public Set<DirectedEdge<T>> adj(T v) {
        return new HashSet<>(adj.getOrDefault(v, Collections.emptySet()));
    }

    public Set<DirectedEdge<T>> edges() {
        Set<DirectedEdge<T>> rs = new HashSet<>();
        adj.values().forEach(rs::addAll);
        return rs;
    }

}

