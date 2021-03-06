package com.github.strangeboy7.graph4j;

import java.util.*;

/**
 * directed graph
 *
 * @param <T> vertex type
 */
public class Digraph<T> extends CommonGraph<T> {

    @Override
    public void addEdge(T t1, T t2) {
        if (t1 == null || t2 == null) return;
        adj.putIfAbsent(t1, Collections.synchronizedSet(new TreeSet<>()));
        adj.putIfAbsent(t2, Collections.synchronizedSet(new TreeSet<>()));
        adj.get(t1).add(t2);
    }

    @Override
    public void removeEdge(T from, T to) {
        if (from == null || to == null) return;
        adj.getOrDefault(from, Collections.emptySet()).remove(to);
    }

    /**
     * the graph with reverse direction
     *
     * @return
     */
    public Digraph<T> reverse() {
        Digraph<T> r = new Digraph<>();
        for (T v : vertex()) {
            for (T w : adj(v)) {
                r.addEdge(w, v);
            }
        }
        return r;
    }

    /**
     * whether the graph has cycle
     *
     * @return
     */
    public boolean hasCycle() {
        DigraphCycle<T> dc = new DigraphCycle<>(this);
        return dc.hasCycle();
    }

    /**
     * the cycle of the graph
     *
     * @return
     */
    public List<T> cycle() {
        DigraphCycle<T> dc = new DigraphCycle<>(this);
        return new ArrayList<>(dc.cycle());
    }


}