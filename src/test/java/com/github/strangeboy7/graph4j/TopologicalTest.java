package com.github.strangeboy7.graph4j;

import org.junit.Test;

import java.io.InputStream;
import java.util.Scanner;

import static org.junit.Assert.*;

public class TopologicalTest {
    private Digraph<Integer> initGraph(String f) {
        Digraph<Integer> graph = new Digraph<>();
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(f);
        Scanner scanner = new Scanner(is);
        while (scanner.hasNextInt()) {
            int i1 = scanner.nextInt();
            int i2 = scanner.nextInt();
            graph.addEdge(i1, i2);
        }
        return graph;
    }

    @Test
    public void order() {
        Digraph<Integer> digraph = initGraph("tinyTG.txt");
        Topological<Integer> topological = new Topological<>(digraph);
        assertTrue(topological.isDAG());
        System.out.println(topological.order());
    }
}