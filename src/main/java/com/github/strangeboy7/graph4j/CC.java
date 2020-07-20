package com.github.strangeboy7.graph4j;

import java.util.HashMap;
import java.util.Map;

/**
 * Connected component calculation for Graph only.
 * using {@link CC#count()} determine how many Connected Component,
 * and {@link CC#id(T t)} find which Connected Component the vertex belongs to
 * Connected Component start with index 0
 *
 * @see KosarajuSCC for directed graph
 * @param <T> same with graph vertex type
 */
public class CC<T> {
    private Map<T, Boolean> marked;
    private Map<T, Integer> id;
    private int count;

    public CC(Graph<T> graph) {
        marked = new HashMap<>(graph.vertex().size());
        id = new HashMap<>(graph.vertex().size());
        graph.vertex().forEach(x -> marked.putIfAbsent(x, false));
        graph.vertex().forEach(x -> id.putIfAbsent(x, 0));
        for (T t : graph.vertex()) {
            if (!marked.get(t)) {
                dfs(graph, t);
                count++;
            }
        }
    }

    private void dfs(Graph<T> g, T t) {
        marked.put(t, true);
        id.put(t, count);
        for (T n : g.adj(t)) {
            if (!marked.get(n)) {
                dfs(g, n);
            }
        }
    }

    /**
     * two vertex is connected
     * @param t1
     * @param t2
     * @return
     */
    public boolean connected(T t1, T t2) {
        if (t1 == null || t2 == null || !id.containsKey(t1) || !id.containsKey(t2)) {
            return false;
        }
        return id.get(t1).equals(id.get(t2));
    }

    /**
     * the given vertex is in which Connected Components
     * @param t vertex
     * @return Connected Component index(start with 0). If t is not in graph, return -1 instead.
     */
    public int id(T t) {
        return id.getOrDefault(t, -1);
    }

    /**
     * how many Connected Components
     * @return
     */
    public int count() {
        return count;
    }
}
