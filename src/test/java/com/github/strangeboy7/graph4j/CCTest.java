package com.github.strangeboy7.graph4j;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class CCTest {

    @Test
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
    public void connected() {
        Graph<Integer> g = initGraph("mediumG.txt");
        CC<Integer> cc = new CC(g);
        System.out.println(cc.count());
        System.out.println("=========");
        for (Integer v : g.vertex()) {
            System.out.println("=========");
            System.out.println(cc.id(v));
        }
    }
}