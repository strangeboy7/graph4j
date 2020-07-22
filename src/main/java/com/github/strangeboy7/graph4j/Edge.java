package com.github.strangeboy7.graph4j;

import java.util.Objects;

/**
 * undirected edge with weight
 * very simple with two vertexes and weight of the edge
 * @see EdgeWeightedGraph
 * @param <T>
 */
public class Edge<T> implements Comparable<Edge<T>>{
    private final T v;
    private final T w;
    private final double weight;

    public Edge(T v, T w, double weight) {
        Objects.requireNonNull(v);
        Objects.requireNonNull(w);

        this.v = v;
        this.w = w;
        this.weight =weight;
    }

    public double weight() {
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
        return Double.compare(weight, e.weight);
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
