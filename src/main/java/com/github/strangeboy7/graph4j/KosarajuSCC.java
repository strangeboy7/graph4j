package com.github.strangeboy7.graph4j;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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
        Objects.requireNonNull(digraph);

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
        if (v == null || !id.containsKey(v) || w == null || !id.containsKey(w)) return false;
        return id.get(v).equals(id.get(w));
    }

    /**
     * the given vertex is in which Connected Components
     * @param v given vertex
     * @return Connected Components id. -1 if v is not in graph
     */
    public int id(T v) {
        return id.getOrDefault(v, -1);
    }

    /**
     * how many Connected Components
     * @return
     */
    public int count() {
        return count;
    }

}
