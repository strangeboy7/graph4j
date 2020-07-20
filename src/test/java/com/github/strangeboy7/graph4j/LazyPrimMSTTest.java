package com.github.strangeboy7.graph4j;

import org.junit.Test;

import java.io.InputStream;
import java.util.Scanner;

import static org.junit.Assert.*;

public class LazyPrimMSTTest {

    private EdgeWeightedGraph<Integer> initGraph(String f) {
        EdgeWeightedGraph<Integer> graph = new EdgeWeightedGraph<>();
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(f);
        Scanner scanner = new Scanner(is);
        while (scanner.hasNextInt()) {
            int i1 = scanner.nextInt();
            int i2 = scanner.nextInt();
            double d = scanner.nextDouble();
            Edge<Integer> e = new Edge<>(i1, i2, d);
            graph.addEdge(e);
        }
        return graph;
    }

    @Test
    public void edges() {
        EdgeWeightedGraph<Integer> graph = initGraph("tinyEWG.txt");
        LazyPrimMST<Integer> mst = new LazyPrimMST<>(graph);
        System.out.println(mst.edges());
    }

    @Test
    public void edges2() {
        EdgeWeightedGraph<Integer> graph = initGraph("tinyEWG.txt");
        PrimMST<Integer> mst = new PrimMST<>(graph);
        System.out.println(mst.edges());
    }
}