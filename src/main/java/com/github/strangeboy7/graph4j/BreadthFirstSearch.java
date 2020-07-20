package com.github.strangeboy7.graph4j;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Queue;

class BreadthFirstSearch<T> extends CommonSearch<T> {
    public BreadthFirstSearch(IGraph<T> g, T t) {
        marked = new HashMap<>(g.vertex().size());
        start = t;
        g.vertex().forEach(x -> marked.putIfAbsent(x, false));
        bfs(g, t);
    }

    private void bfs(IGraph<T> g, T s) {
        Queue<T> queue = new ArrayDeque<>();
        marked.put(s, true);
        queue.add(s);
        while (!queue.isEmpty()) {
            T t = queue.remove();
            for (T n : g.adj(t)) {
                if (!marked.get(n)) {
                    edgeTo.put(n, t);
                    marked.put(n, true);
                    count++;
                    queue.add(n);
                }
            }
        }
    }
}
