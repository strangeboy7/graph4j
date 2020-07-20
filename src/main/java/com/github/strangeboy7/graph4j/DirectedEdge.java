package com.github.strangeboy7.graph4j;

/**
 * directed edge with weight
 * very simple with from, to vertexes and weight of the edge
 * @see EdgeWeightedDigraph
 * @param <T>
 */
public class DirectedEdge<T> implements Comparable<DirectedEdge<T>> {
    private T from;
    private T to;
    private double weight;

    public DirectedEdge(T from, T to, double weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    @Override
    public int compareTo(DirectedEdge<T> o) {
        return Double.compare(weight, o.weight);
    }

    public T from() {
        return from;
    }

    public T to() {
        return to;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "DirectEdge{" +
                "from=" + from +
                ", to=" + to +
                ", weight=" + weight +
                '}';
    }
}
