package com.github.strangeboy7.graph4j;

import org.junit.Test;

import java.io.*;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

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


}