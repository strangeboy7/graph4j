package com.github.strangeboy7.graph4j;

public class Edge<T> implements Comparable<Edge<T>>{
    private final T v;
    private final T w;
    private final Comparable weight;

    public Edge(T v, T w, Comparable weight) {
        this.v = v;
        this.w = w;
        this.weight =weight;
    }

    public Comparable weight() {
        return weight;
    }

    public T either() {
        return v;
    }

    public T other(T v) {
        if (this.v == v) return this.w;
        else if (this.w == v) return this.v;
        else throw new RuntimeException("Inconsistent edge");
    }

    @Override
    public int compareTo(Edge e) {
        return this.weight.compareTo(e.weight);
    }

    @Override
    public String toString() {
        return "Edge{" +
                "v=" + v +
                ", w=" + w +
                ", weight=" + weight +
                '}';
    }
}
