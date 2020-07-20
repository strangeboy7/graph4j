package com.github.strangeboy7.graph4j;

import java.util.HashMap;
import java.util.Map;

public class KosarajuSCC<T> {
    private Map<T, Boolean> marked = new HashMap<>();
    private Map<T, Integer> id = new HashMap<>();
    private int count;

    public KosarajuSCC(Digraph<T> digraph) {
        digraph.vertex().forEach(v -> marked.putIfAbsent(v, Boolean.FALSE));
        DeepFirstSearchOrder<T> order = new DeepFirstSearchOrder<T>(digraph.reverse());
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

    public boolean stronglyConnected(T v, T w) {
        return id.get(v).equals(id.get(w));
    }

    public int id(T v) {
        return id.get(v);
    }

    public int count() {
        return count;
    }

}
