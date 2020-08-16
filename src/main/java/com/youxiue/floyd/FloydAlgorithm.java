package com.youxiue.floyd;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created by xfb on 2020/08/16 20:32.
 * 弗洛伊德算法
 */
public class FloydAlgorithm {

    @Test
    public void test() {
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        //邻接矩阵
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 65535;// 表示不可以连接
        matrix[0] = new int[]{0, 5, 7, N, N, N, 2};
        matrix[1] = new int[]{5, 0, N, 9, N, N, 3};
        matrix[2] = new int[]{7, N, 0, N, 8, N, N};
        matrix[3] = new int[]{N, 9, N, 0, N, 4, N};
        matrix[4] = new int[]{N, N, 8, N, 0, 5, 4};
        matrix[5] = new int[]{N, N, N, 4, 5, 0, 6};
        matrix[6] = new int[]{2, 3, N, N, 4, 6, 0};
        Graph graph = new Graph(vertex, matrix);

        for (int k = 0; k < vertex.length; k++) { // 最外层循环  中间节点

            for (int i = 0; i < vertex.length; i++) { // 循环获取起始节点

                for (int j = 0; j < vertex.length; j++) { // 循环获取 结束节点

                    int len = graph.dis[i][k] + graph.dis[k][j];
                    if (len < graph.dis[i][j]) {
                        graph.dis[i][j] = len;
                        graph.pre[i][j] = graph.pre[k][j];
                        //graph.pre[i][j] =k;
                    }
                }
            }
        }

        graph.print();
    }

}

class Graph {
    char[] vertexs; // 顶点集合

    int[][] dis;// 距离集合

    int[][] pre;// 前驱(中间)节点集合

    public Graph(char[] vertexs, int[][] dis) {
        this.vertexs = vertexs;
        this.dis = dis;

        this.pre = new int[dis.length][dis.length];
        // 初始化 前驱节点集合
        for (int i = 0; i < dis.length; i++) {
            Arrays.fill(pre[i], i);
        }
    }

    public void print() {

        for (int i = 0; i < dis.length; i++) {
            for (int j = 0; j < dis[i].length; j++) {
                System.out.print(vertexs[pre[i][j]]+ "   ");
            }
            System.out.println();
            for (int j = 0; j < dis[i].length; j++) {
                System.out.print("[" + vertexs[i] + "->" + vertexs[j] + " = " + dis[i][j] + "], ");
            }
            System.out.println();
        }
    }
}
