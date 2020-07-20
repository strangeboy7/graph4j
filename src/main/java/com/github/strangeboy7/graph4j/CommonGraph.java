package com.github.strangeboy7.graph4j;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

abstract class CommonGraph<T> implements IGraph<T> {
    protected ConcurrentMap<T, Set<T>> adj = new ConcurrentHashMap<>();

    public Set<T> vertex() {
        return new HashSet<>(adj.keySet());
    }

    public Set<T> adj(T t) {
        return new HashSet<>(adj.getOrDefault(t, Collections.emptySet()));
    }

    public Set<T> getConnected(T t) {
        ISearch<T> s = new DeepFirstSearch<T>(this, t);
        return vertex().stream().filter(s::marked).collect(Collectors.toSet());
    }

    public boolean connected(T from, T to) {
        return getConnected(from).contains(to);
    }

    public List<T> pathTo(T from, T to) {
        ISearch<T> s = new BreadthFirstSearch<>(this, from);
        return s.pathTo(to);
    }

}
