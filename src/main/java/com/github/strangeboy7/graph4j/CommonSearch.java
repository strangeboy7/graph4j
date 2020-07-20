package com.github.strangeboy7.graph4j;

import java.util.*;

abstract class CommonSearch<T> implements ISearch<T> {
    protected Map<T, Boolean> marked;
    protected Map<T, T> edgeTo = new HashMap<>();

    protected int count;
    protected T start;

    @Override
    public boolean marked(T t) {
        return marked.getOrDefault(t, false);
    }

    @Override
    public List<T> pathTo(T t) {
        Stack<T> stack = new Stack<>();
        for (T i = t; edgeTo.containsKey(i); i = edgeTo.get(i)) {
            stack.push(i);
        }
        stack.push(start);
        Collections.reverse(stack);
        return stack;
    }

    @Override
    public int count() {
        return count;
    }
}
