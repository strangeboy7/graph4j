package com.github.strangeboy7.graph4j;

import org.junit.Test;

import java.io.*;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.*;

public class GraphTest {
    public void testInit() throws IOException {
        initGraph("tinyG.txt");
    }

    private Graph<Integer> initGraph(String f) {
        Graph<Integer> graph = new Graph<>();
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
    public void testConnection() {
        Graph<Integer> graph = initGraph("tinyG.txt");
//        graph.getConnected(10).forEach(System.out::println);
        graph.getConnected(1).forEach(System.out::println);
    }

    @Test
    public void testPath() {
        Graph<Integer> graph = initGraph("tinyCG.txt");
        List<Integer> path = graph.pathTo(1, 3);
        System.out.println(path);
    }

    @Test
    public void testRemoveEdge() {
        Graph<Integer> graph = initGraph("tinyCG.txt");
        graph.removeEdge(2, 3);
        assertFalse(graph.adj(2).contains(3));

        graph = initGraph("tinyCG.txt");
        graph.removeEdge(7, 8);
    }

    @Test
    public void testRemoveVertex() {
        Graph<Integer> graph = initGraph("tinyCG.txt");
        graph.removeVertex(2);
        assertTrue(graph.adj(2).isEmpty());
        for (Integer v : graph.vertex()) {
            assertFalse(graph.adj(v).contains(2));
        }
    }
}