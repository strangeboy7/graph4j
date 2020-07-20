package com.github.strangeboy7.graph4j;

import java.util.*;

/**
 * Dijkstra shortest path algorithm
 * the start vertex is set in constructor parameter
 * using {@link DijkstraSP#distTo(T t)} to get distance,
 * or {@link DijkstraSP#pathTo(T t)} to get the path
 * @param <T>
 */
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

    /**
     * distance to the vertex (start vertex set in constructor)
     * @param v vertex
     * @return distance
     */
    public double distTo(T v) {
        return distTo.get(v);
    }

    /**
     * has path to the vertex (start vertex set in constructor)
     * @param v vertex
     * @return has path to the vertex
     */
    public boolean hasPathTo(T v) {
        return distTo.get(v) < Double.POSITIVE_INFINITY;
    }

    /**
     * path to the vertex (start vertex set in constructor)
     * @param v vertex
     * @return path to the vertex
     */
    public List<DirectedEdge<T>> pathTo(T v) {
        if (!hasPathTo(v)) return null;
        List<DirectedEdge<T>> path = new LinkedList<>();
        for (DirectedEdge<T> e = edgeTo.get(v); e != null; e = edgeTo.get(e.from())) {
            path.add(e);
        }
        Collections.reverse(path);
        return path;
    }
}
