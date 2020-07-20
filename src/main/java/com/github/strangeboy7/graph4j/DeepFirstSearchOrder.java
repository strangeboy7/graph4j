package com.github.strangeboy7.graph4j;

import java.util.*;

class DeepFirstSearchOrder<T> implements ISearchOrder<T> {
    private List<T> pre = new ArrayList<>();
    private List<T> post = new ArrayList<>();
    private Map<T, Boolean> marked = new HashMap<>();

    public DeepFirstSearchOrder(IGraph<T> graph) {
        graph.vertex().forEach(x -> marked.putIfAbsent(x, false));
        for (T v : graph.vertex()) {
            if (!marked.get(v)) {
                dfs(graph, v);
            }
        }
    }

    protected void dfs(IGraph<T> g, T t) {
        pre.add(t);
        marked.put(t, true);
        for (T n : g.adj(t)) {
            if (!marked.get(n)) {
                dfs(g, n);
            }
        }
        post.add(t);
    }

    @Override
    public List<T> pre() {
        return new ArrayList<>(pre);
    }

    @Override
    public List<T> post() {
        return new ArrayList<>(post);
    }

    @Override
    public List<T> reversePost() {
        List<T> rp = post();
        Collections.reverse(rp);
        return rp;
    }
}
