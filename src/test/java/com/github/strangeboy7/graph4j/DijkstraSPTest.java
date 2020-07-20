package com.github.strangeboy7.graph4j;

import org.junit.Test;

import java.io.InputStream;
import java.util.Scanner;

import static org.junit.Assert.*;

public class DijkstraSPTest {

    private EdgeWeightedDigraph<Integer> initGraph(String f) {
        EdgeWeightedDigraph<Integer> graph = new EdgeWeightedDigraph<>();
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(f);
        Scanner scanner = new Scanner(is);
        while (scanner.hasNextInt()) {
            int i1 = scanner.nextInt();
            int i2 = scanner.nextInt();
            double d = scanner.nextDouble();
            DirectedEdge<Integer> e = new DirectedEdge<>(i1, i2, d);
            graph.addEdge(e);
        }
        return graph;
    }

    @Test
    public void pathTo() {
        EdgeWeightedDigraph<Integer> graph = initGraph("tinyEWD.txt");
        DijkstraSP<Integer> sp = new DijkstraSP<>(graph, 0);
        System.out.println(sp.pathTo(6));
    }
}