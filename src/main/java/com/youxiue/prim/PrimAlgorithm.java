package com.youxiue.prim;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created by xfb on 2020/08/13 21:51.
 * <p>
 * 普利姆算法
 * 找到 链接所有村庄, 路的总长度最小的路径
 */
public class PrimAlgorithm {


    @Test
    public void test() {
        char[] data = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int verxs = data.length;
        int[][] weight = {
                {10000, 5, 7, 10000, 10000, 10000, 2},
                {5, 10000, 10000, 9, 10000, 10000, 3},
                {7, 10000, 10000, 10000, 8, 10000, 10000},
                {10000, 9, 10000, 10000, 10000, 4, 10000},
                {10000, 10000, 8, 10000, 10000, 5, 4},
                {10000, 10000, 10000, 4, 5, 10000, 6},
                {2, 3, 10000, 10000, 4, 6, 10000},
        };

        MGraph mGraph = new MGraph(verxs);
        mGraph.weight = weight;
        mGraph.data = data;

        mGraph.show();
        prim(mGraph, 5);
    }


    /**
     * @param graph 图
     * @param v     从图的第几个定点开始生成
     */
    public void prim(MGraph graph, int v) {

        //定义一个数组 用于存放 定点是否被访问过
        int[] visited = new int[graph.verxs];

        visited[v] = 1;

        // n 个顶点  n-1 个边
        for (int k = 0; k < graph.verxs - 1; k++) {
            int h1 = -1;
            int h2 = -1;
            int minWeight = 10000;
            for (int i = 0; i < graph.verxs; i++) { // 已经被标记过得顶点
                if (visited[i] == 1) {
                    for (int j = 0; j < graph.verxs; j++) {
                        // 如果是没有被标记过得点,  并且 长度 比较小
                        if (visited[j] == 0 && graph.weight[i][j] < minWeight) {
                            minWeight = graph.weight[i][j];
                            h1 = i;
                            h2 = j;
                        }
                    }
                }
            }
            System.out.println("<" + graph.data[h1] + ", " + graph.data[h2] + ">"+ "  weight:"+ graph.weight[h1][h2]);
            // 将 j 节点标记为 已经标记过
            visited[h2] = 1;
        }
    }

}

class MiTree {

    public void createGraph(MGraph graph, int verxs, char[] data, int[][] weight) {
        graph.verxs = verxs;
        graph.data = data;
        graph.weight = weight;
    }
}


class MGraph {
    int verxs; // 图的节点个数
    // 图的顶点集合
    char[] data;
    // 图的矩阵
    int[][] weight;

    public MGraph(int verxs) {
        this.verxs = verxs;
        this.data = new char[verxs];
        this.weight = new int[verxs][verxs];
    }


    public void show() {
        for (int i = 0; i < verxs; i++) {
            System.out.println(Arrays.toString(weight[i]));
        }
    }

}
