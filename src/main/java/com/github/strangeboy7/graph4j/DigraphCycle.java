package com.github.strangeboy7.graph4j;

import java.util.*;

/**
 * directed graph cycle detective helper class
 * used in {@link Digraph#hasCycle()} and {@link Digraph#cycle()}
 * @param <T>
 */
class DigraphCycle<T> {
    private Map<T, Boolean> marked = new HashMap<>();
    private Map<T, Boolean> onStack = new HashMap<>();
    private Map<T, T> edgeTo = new HashMap<>();
    private Stack<T> cycle = new Stack<>();

    public DigraphCycle(Digraph<T> digraph) {
        for (T v : digraph.vertex()) {
            marked.putIfAbsent(v, false);
            onStack.putIfAbsent(v, false);
        }
        for (T v : digraph.vertex()) {
            if (!marked.get(v)) {
                dfs(digraph, v);
            }
        }
    }

    private void dfs(IGraph<T> g, T t) {
        onStack.put(t, true);
        marked.put(t, true);
        for (T n : g.adj(t)) {
            if (hasCycle()) {
                return;
            } else if (!marked.get(n)) {
                edgeTo.put(n, t);
                dfs(g, n);
            } else if (onStack.get(n)) {
                for (T i = t; !i.equals(n); i = edgeTo.get(i)) {
                    cycle.push(i);
                }
                cycle.push(n);
                cycle.push(t);
            }
        }
        onStack.put(t, false);
    }

    public boolean hasCycle() {
        return !cycle.isEmpty();
    }

    public List<T> cycle() {
        return new ArrayList<>(cycle);
    }


}
