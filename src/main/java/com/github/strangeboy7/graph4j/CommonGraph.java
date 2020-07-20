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

    public int edge() {
        return adj.values().stream().mapToInt(Set::size).sum() / 2;
    }

    public int degree(T t) {
        Set<T> list = adj.get(t);
        return list == null ? 0 : list.size();
    }

    public int maxDegree() {
        return adj.keySet().stream().map(this::degree).max(Integer::compareTo).orElse(0);
    }

    public Set<T> getConnected(T t) {
        ISearch<T> s = new DeepFirstSearch<T>(this, t);
        return vertex().stream().filter(s::marked).collect(Collectors.toSet());
    }

    public boolean connected(T t1, T t2) {
        return getConnected(t1).contains(t2);
    }

    public List<T> pathTo(T from, T to) {
        ISearch<T> s = new BreadthFirstSearch<>(this, from);
        return s.pathTo(to);
    }

}
