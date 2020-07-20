package com.github.strangeboy7.graph4j;

import java.util.*;

public class DijkstraSP<T> {
    private final Map<T, DirectedEdge<T>> edgeTo;
    private final Map<T, Double> distTo;
    private final IndexedPQ<T, Double> pq;

    public DijkstraSP(EdgeWeightedDigraph<T> graph, T s) {
        edgeTo = new HashMap<>();
        distTo = new HashMap<>();
        graph.vertex().forEach(x -> distTo.putIfAbsent(x, Double.POSITIVE_INFINITY));
        pq = new IndexedPQ<>();
        pq.insert(s, 0d);
        distTo.put(s, 0d);
        while (!pq.isEmpty()) relax(graph, pq.pollIndex());
    }

    private void relax(EdgeWeightedDigraph<T> graph, T v) {
        for (DirectedEdge<T> e : graph.adj(v)) {
            T w = e.to();
            if (distTo.get(w) > distTo.get(v) + e.getWeight()) {
                distTo.put(w, distTo.get(v) + e.getWeight());
                edgeTo.put(w, e);
                if (pq.contains(w)) pq.change(w, distTo.get(w));
                else pq.insert(w, distTo.get(w));
            }
        }
    }

    public double distTo(T v) {
        return distTo.get(v);
    }

    public boolean hasPathTo(int v) {
        return distTo.get(v) < Double.POSITIVE_INFINITY;
    }

    public List<DirectedEdge<T>> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        List<DirectedEdge<T>> path = new LinkedList<>();
        for (DirectedEdge<T> e = edgeTo.get(v); e != null; e = edgeTo.get(e.from())) {
            path.add(e);
        }
        Collections.reverse(path);
        return path;
    }
}
