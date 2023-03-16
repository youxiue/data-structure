package com.youxiue.kruskal;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created by xfb on 2020/08/15 20:30.
 * 克鲁斯卡尔kruskal 算法 选取最短路径
 * 1. 排序
 * 2. 选出最短的边, 与其他边不构成回路
 */
public class KruskalCase {
    public final Integer MAX = Integer.MAX_VALUE;

    @Test
    public void test() {
        // 1. 构建 图形
        char[] vertexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = {
                // A      B       C       D       E       F       G
                {0, 12, MAX, MAX, MAX, 16, 14},    // A
                {12, 0, 10, MAX, MAX, 7, MAX},   // B
                {MAX, 10, 0, 3, 5, 6, MAX},   // C
                {MAX, MAX, 3, 0, 4, MAX, MAX},   // D
                {MAX, MAX, 5, 4, 0, 2, 8},     // E
                {16, 7, 6, MAX, 2, 0, 9},     // F
                {14, MAX, MAX, MAX, 8, 9, 0}      // G
        };
        Kruskal kruskal = new Kruskal(vertexs, matrix);
        kruskal.print();
        // 2. 获取边集合
        EData[] edges = getEdges(kruskal);

        // 3. 对边集合 进行排序
        System.out.println("排序前: ");
        printEdges(edges);
        Arrays.sort(edges);
        System.out.println("排序后: ");
        printEdges(edges);

        int length = kruskal.vertexs.length;

        // 4. 选取最短的边, 并且与其他选中的边 不构成回路.
        // 记录选中的边
        EData[] selectEData = new EData[length - 1];
        // 记录 终点的集合
        int[] ends = new int[length];
        int index = 0;

        for (EData edge : edges) {
            // 获取该边的 一个顶点索引
            int s = getPosition(kruskal.vertexs, edge.start);
            // 获取该边的 另一个顶点索引
            int e = getPosition(kruskal.vertexs, edge.end);

            // 顶点s 的终点
            int m = getEnd(ends, s);
            int n = getEnd(ends, e);

            // 如果两个顶点的 终点 不一样 则不会构成闭环
            if (m != n) {
                selectEData[index++] = edge;
                ends[m] = n;
            }
        }
        System.out.println("选中的边");
        printEdges(selectEData);
    }

    // 生成边的方法
    public EData[] getEdges(Kruskal kruskal) {
        EData[] edges = new EData[kruskal.edgeNum];
        int index = 0;
        for (int i = 0; i < kruskal.matrix.length; i++) {
            for (int j = i + 1; j < kruskal.matrix.length; j++) {
                if (kruskal.matrix[i][j] != MAX) {
                    EData eData = new EData(kruskal.vertexs[i], kruskal.vertexs[j], kruskal.matrix[i][j]);
                    edges[index++] = eData;
                }
            }
        }
        return edges;
    }

    //打印边的方法
    public void printEdges(EData[] edges) {
        for (EData edge : edges) {
            System.out.println(edge);
        }
    }

    // 获取顶点的索引
    public int getPosition(char[] vertexs, char a) {
        for (int i = 0; i < vertexs.length; i++) {
            if (vertexs[i] == a) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 获取一个顶点的 终点的 索引
     *
     * @param ends 索引是 一条边的起始点,  值是一条边对应的终点,
     * @param i    要查找终点的起始点
     */
    public int getEnd(int[] ends, int i) {
        while (ends[i] != 0) {
            i = ends[i];
        }
        return i;
    }

}

/**
 * 边对象
 */
class EData implements Comparable<EData> {
    char start;  // 一条边的一个顶点
    char end; // 一条边的另一个顶点
    int weight; // 权重

    public EData(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "<" + start +
                "," + end +
                "> =" + weight;
    }

    @Override
    public int compareTo(EData o) {
        return this.weight - o.weight;
    }
}


class Kruskal {
    int edgeNum; // 边的数量
    char[] vertexs; // 顶点集合
    int[][] matrix; // 邻接矩阵

    public Kruskal(char[] vertexs, int[][] matrix) {
        this.vertexs = vertexs;
        this.matrix = matrix;

        // 算出 边的数量
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i + 1; j < matrix.length; j++) {
                if (matrix[i][j] != Integer.MAX_VALUE) {
                    this.edgeNum++;
                }
            }
        }
    }

    // 打印的方法
    public void print() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.printf("%15d", matrix[i][j]);
            }
            System.out.println();
        }
    }
}