package com.github.strangeboy7.graph4j;

import java.util.*;

class DeepFirstSearch<T> extends CommonSearch<T> {
    public DeepFirstSearch(IGraph<T> g, T t) {
        Objects.requireNonNull(g);
        Objects.requireNonNull(t);
        marked = new HashMap<>(g.vertex().size());
        start = t;
        g.vertex().forEach(x -> marked.putIfAbsent(x, false));
        dfs(g, t);
    }

    protected void dfs(IGraph<T> g, T t) {
        marked.put(t, true);
        count++;
        for (T n : g.adj(t)) {
            if (!marked.get(n)) {
                edgeTo.put(n, t);
                dfs(g, n);
            }
        }
    }

}
