package com.github.strangeboy7.graph4j;

import java.util.*;

public class LazyPrimMST<T> {
    private final Map<T, Boolean> marked = new HashMap<>();
    private final Queue<Edge<T>> mst = new ArrayDeque<>();
    private final PriorityQueue<Edge<T>> pq = new PriorityQueue<>();

    public LazyPrimMST(EdgeWeightedGraph<T> graph) {
        if (!isConnected(graph)) {
            throw new IllegalArgumentException("graph must be connected");
        }
        if (graph.vertexCount() == 0) return;

        graph.vertex().forEach(v -> marked.putIfAbsent(v, Boolean.FALSE));
        visit(graph, graph.vertex().iterator().next());
        while (!pq.isEmpty()) {
            Edge<T> e = pq.poll();
            T v = e.either(), w = e.other(v);
            if (marked.get(v) && marked.get(w)) continue;
            mst.add(e);
            if (!marked.get(v)) visit(graph, v);
            if (!marked.get(w)) visit(graph, w);
        }
    }

    private void visit(EdgeWeightedGraph<T> graph, T v) {
        marked.put(v, true);
        for (Edge<T> edge : graph.adj(v)) {
            if (!marked.get(edge.other(v))) pq.add(edge);
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
        return new ArrayList<>(mst);
    }
}
