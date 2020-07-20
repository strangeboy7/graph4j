package com.github.strangeboy7.graph4j;

import java.util.List;
import java.util.Set;

/**
 * graph interface
 * @see Graph
 * @see Digraph
 * @param <T> graphic vertex type
 */
public interface IGraph<T> {
    /**
     * add edge
     * the parameter order is important in Digraph
     * @param from
     * @param to
     */
    void addEdge(T from, T to);

    /**
     * add vertex to graph
     * @param v
     */
    void addVertex(T v);

    /**
     * remove edge from graph
     * @param from
     * @param to
     */
    void removeEdge(T from, T to);

    /**
     * remove vertex from graph
     * @param t
     */
    void removeVertex(T t);

    /**
     * @return all the vertexes
     */
    Set<T> vertex();

    /**
     * @param t vertex
     * @return the adjective vertexes of the given vertex
     */
    Set<T> adj(T t);

    /**
     * all connected vertexes
     * @param t the given vertex
     * @return all connected vertexes
     *
     * @see CC
     * @see KosarajuSCC
     */
    Set<T> getConnected(T t);

    /**
     * whether the 'from' vertex can reach to 'to' vertex
     * @param from start vertex
     * @param to target vertex
     * @return
     * @see CC
     * @see KosarajuSCC
     */
    boolean connected(T from, T to);

    /**
     * the path of the 'from' to 'to' vertex
     * @param from start vertex
     * @param to target vertex
     * @return
     * @see CC
     * @see KosarajuSCC
     */
    List<T> pathTo(T from, T to);
}
