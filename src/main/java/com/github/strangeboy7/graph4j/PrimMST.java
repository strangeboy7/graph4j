package com.github.strangeboy7.graph4j;

import java.util.*;

/**
 * minimum spanning tree algorithm
 * @see LazyPrimMST
 * @param <T>
 */
public class PrimMST<T> {
    private final Map<T, Boolean> marked = new HashMap<>();
    private final Map<T, Edge<T>> mst = new HashMap<>();
    IndexedPQ<T, Edge<T>> pq = new IndexedPQ<>();

    public PrimMST(EdgeWeightedGraph<T> graph) {
        Objects.requireNonNull(graph);

        if (!isConnected(graph)) {
            throw new IllegalArgumentException("graph must be connected");
        }
        if (graph.vertexCount() == 0) return;

        graph.vertex().forEach(v -> marked.putIfAbsent(v, Boolean.FALSE));

        visit(graph, graph.vertex().iterator().next());
        while (!pq.isEmpty())
            visit(graph, pq.pollIndex());
    }


    private void visit(EdgeWeightedGraph<T> graph, T v) {
        marked.put(v, true);
        for (Edge<T> edge : graph.adj(v)) {
            T w = edge.other(v);
            if (marked.get(w)) continue;
            Edge<T> e = mst.get(w);
            if (e == null || edge.compareTo(e) < 0) {
                mst.put(w, edge);
                if (pq.contains(w)) {
                    pq.change(w, edge);
                } else {
                    pq.insert(w, edge);
                }
            }

        }
    }

    private boolean isConnected(EdgeWeightedGraph<T> ewGraph) {
        Graph<T> g = new Graph<>();
        for (Edge<T> edge : ewGraph.edges()) {
            T v = edge.either();
            g.addEdge(v, edge.other(v));
        }
        CC<T> cc = new CC<>(g);
        return cc.count() == 1;
    }

    public List<Edge<T>> edges() {
        return new ArrayList<>(mst.values());
    }
}
