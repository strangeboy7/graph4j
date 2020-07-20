package com.github.strangeboy7.graph4j;

import java.util.HashMap;
import java.util.Map;

/**
 * strong connected components algorithm for {@link Digraph}.
 * using {@link KosarajuSCC#count()} determine how many Connected Component,
 * and {@link KosarajuSCC#id(T t)} find which Connected Component the vertex belongs to
 * Connected Component start with index 0
 * @param <T>
 */
public class KosarajuSCC<T> {
    private Map<T, Boolean> marked = new HashMap<>();
    private Map<T, Integer> id = new HashMap<>();
    private int count;

    public KosarajuSCC(Digraph<T> digraph) {
        digraph.vertex().forEach(v -> marked.putIfAbsent(v, Boolean.FALSE));
        DeepFirstSearchOrder<T> order = new DeepFirstSearchOrder<>(digraph.reverse());
        for (T v : order.reversePost()) {
            if (!marked.get(v)) {
                dfs(digraph, v);
                count++;
            }
        }
    }

    private void dfs(Digraph<T> digraph, T v) {
        marked.put(v, Boolean.TRUE);
        id.put(v, count);
        for (T t : digraph.adj(v)) {
            if (!marked.get(t)) {
                dfs(digraph, t);
            }
        }
    }

    /**
     * two vertex is strongly connected
     * @param v
     * @param w
     * @return
     */
    public boolean stronglyConnected(T v, T w) {
        return id.get(v).equals(id.get(w));
    }

    /**
     * the given vertex is in which Connected Components
     * @param v
     * @return
     */
    public int id(T v) {
        return id.get(v);
    }

    /**
     * how many Connected Components
     * @return
     */
    public int count() {
        return count;
    }

}
