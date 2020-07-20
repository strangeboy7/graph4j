package com.github.strangeboy7.graph4j;

import org.junit.Test;

import java.io.InputStream;
import java.util.Scanner;

import static org.junit.Assert.*;

public class DigraphTest {
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
    public void reverse() {
        Digraph<Integer> digraph = initGraph("tinyDG.txt");
//        System.out.println(digraph.adj(6));
        System.out.println(digraph.getConnected(9));

    }

    @Test
    public void testRemoveEdge() {
        Digraph<Integer> graph = initGraph("tinyCG.txt");
        graph.removeEdge(2, 3);
        assertFalse(graph.adj(2).contains(3));
    }

    @Test
    public void testRemoveVertex() {
        Digraph<Integer> graph = initGraph("tinyCG.txt");
        graph.removeVertex(2);
        assertTrue(graph.adj(2).isEmpty());
        for (Integer v : graph.vertex()) {
            assertFalse(graph.adj(v).contains(2));
        }
    }
}