package com.youxiue.graph;

import org.junit.Test;

/**
 * Created by xfb on 2020/08/04 21:34.
 * 图形
 */
public class GraphDemo {

    @Test
    public void test() {

        // A_B A_C B_C B_D B_E
        String[] arr = {"A", "B", "C", "D", "E"};
        Graph graph = new Graph(arr);

        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);

        graph.printEdges();
        int num = graph.getNumOfEdges();
        System.out.println(num);

        //graph.dfs();
        graph.bfs();
    }

}
