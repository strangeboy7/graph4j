package com.github.strangeboy7.graph4j;

import java.util.*;
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

    @Override
    public void addVertex(T v) {
        if (v == null) return;
        adj.putIfAbsent(v, Collections.synchronizedSet(new TreeSet<>()));
    }

    @Override
    public void removeVertex(T t) {
        if (t == null) return;
        adj.remove(t);
        adj.values().forEach(x -> x.remove(t));
    }

    public Set<T> getConnected(T t) {
        Objects.requireNonNull(t);
        ISearch<T> s = new DeepFirstSearch<T>(this, t);
        return vertex().stream().filter(s::marked).collect(Collectors.toSet());
    }

    public boolean connected(T from, T to) {
        if (from == null || to == null) return false;
        return getConnected(from).contains(to);
    }

    public List<T> pathTo(T from, T to) {
        Objects.requireNonNull(from);
        Objects.requireNonNull(to);
        ISearch<T> s = new BreadthFirstSearch<>(this, from);
        return s.pathTo(to);
    }

}
