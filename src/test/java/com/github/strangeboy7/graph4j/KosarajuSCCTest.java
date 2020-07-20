package com.github.strangeboy7.graph4j;

import org.junit.Test;

import java.io.InputStream;
import java.util.Scanner;

import static org.junit.Assert.*;

public class KosarajuSCCTest {

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
    public void stronglyConnected() {
        Digraph<Integer> digraph = initGraph("tinyDGC.txt");
        KosarajuSCC<Integer> scc = new KosarajuSCC<>(digraph);
        for (Integer v : digraph.vertex()) {
            System.out.println(v + " ===> " + scc.id(v));
        }
    }
}