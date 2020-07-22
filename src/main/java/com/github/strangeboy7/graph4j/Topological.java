package com.github.strangeboy7.graph4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * topological for the {@link Digraph}
 * @param <T> vertex type
 */
public class Topological<T> {
    private List<T> order = new ArrayList<T>();

    public Topological(Digraph<T> digraph) {
        Objects.requireNonNull(digraph);

        DigraphCycle<T> dc = new DigraphCycle<>(digraph);
        if (!dc.hasCycle()) {
            DeepFirstSearchOrder<T> dfs = new DeepFirstSearchOrder<>(digraph);
            order = dfs.reversePost();
        }
    }

    /**
     * get topological order
     * @return
     */
    public List<T> order() {
        List<T> r = new ArrayList<>(order);
        Collections.reverse(r);
        return r;
    }

    /**
     * graph is directed acyclic graph
     * @return
     */
    public boolean isDAG() {
        return !order.isEmpty();
    }
}
