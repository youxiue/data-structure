package com.youxiue.dijkstra;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created by xfb on 2020/08/16 10:58.
 * 迪杰斯特拉 算法
 * 迪杰斯特拉算法是 典型最短路径算法,  用于计算 某一个节点到其他节点的最短路径
 * 他的主要特点是以起始点为中心向外层层扩展, 直到扩展到终点为止.
 * 广度 +  贪婪
 */
public class DijkstraAlgorithm {

    @Test
    public void test(){
        char[] vertex = { 'A', 'B', 'C', 'D', 'E', 'F', 'G' };
        //邻接矩阵
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 65535;// 表示不可以连接
        matrix[0]=new int[]{N,5,7,N,N,N,2};
        matrix[1]=new int[]{5,N,N,9,N,N,3};
        matrix[2]=new int[]{7,N,N,N,8,N,N};
        matrix[3]=new int[]{N,9,N,N,N,4,N};
        matrix[4]=new int[]{N,N,8,N,N,5,4};
        matrix[5]=new int[]{N,N,N,4,5,N,6};
        matrix[6]=new int[]{2,3,N,N,4,6,N};
        Graph graph = new Graph(vertex, matrix);
        graph.showGraph();
        graph.djst(6);
        graph.printVv();
    }
}

class Graph {
    char[] vertexs; // 顶点集合
    int[][] matrix; // 邻接矩阵
    VisitedVertex vv; // 已经访问过的顶点的集合

    public Graph(char[] vertexs, int[][] matrix) {
        this.vertexs = vertexs;
        this.matrix = matrix;
    }

    // 展示 邻接矩阵
    public void showGraph(){
        for (int i = 0; i < matrix.length; i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }
    }

    // 迪杰斯特拉算法 求最短路径
    public void djst(int start){
        // 初始化 记录信息
        vv = new VisitedVertex(this.matrix.length, start);
        // 更新 出发节点 到其他节点的距离
        update(start);
        // 循环 找到最短路径
        for (int i = 0; i < this.matrix.length; i++) {
            // 获取新的访问节点
            int index = vv.getNew();
            if(index == -1){
                return; // 如果没有找到 说明循环结束了 , 退出
            }
            // 更新访问节点 到顶点的最短距离
            update(index);
        }
    }

    // 找到到顶点的最短距离, 并更新 对应的前置节点 和距离
    public void update(int index){
        for (int i = 0; i < matrix[index].length; i++) {
            // 获取 顶点到index 点的距离 + index 到i点的距离
            int len = vv.getDis(index) + matrix[index][i];
            // 如果该点没有被访问过,  并且 len(顶点经过index再到i的距离) 小于 顶点到 i点的距离
            if(vv.already_arr[i] !=1 &&  len < vv.getDis(i)){
                // 则将 index节点 置为 i节点的 前置节点,   并将 顶点到 i节点的值更新
                vv.dis[i] = len;
                vv.pre_visited[i] = index;
            }
        }
    }

    public void printVv(){
        vv.print();
    }


}

class VisitedVertex {
    int[] already_arr; // 记录节点是否被访问过 , 已访问为1, 未访问为0
    int[] pre_visited; // 每个索引对应的值 为前一个顶点的索引
    int[] dis; // 记录出发顶点到其他所有顶点的距离,


    /**
     * 初始化的方法
     * @param length 长度
     * @param index  出发节点
     */
    public VisitedVertex(int length, int index) {
        int max = 65535;

        this.already_arr = new int[length];
        this.pre_visited = new int[length];
        this.dis = new int[length];
        Arrays.fill(dis, max); // 默认初始值都设为最大值

        // 出发节点 设为已访问
        already_arr[index] = 1;
        // 出发节点到自身的距离设置为 0
        dis[index] = 0;
    }

    // 获取距离
    public int getDis(int index){
        return dis[index];
    }

    // 获取新的访问节点
    public int getNew(){
        int min = 65535;
        int minIndex  = -1;
        for (int i = 0; i < already_arr.length; i++) {
            if(already_arr[i] == 0 && dis[i] < min){
                min = dis[i];
                minIndex = i;
            }
        }
        // 将该节点置为 已访问
        if(minIndex!= -1){
            already_arr[minIndex] = 1;
        }
        return minIndex;
    }

    public void print(){
        System.out.println(Arrays.toString(this.already_arr));
        System.out.println(Arrays.toString(this.pre_visited));
        System.out.println(Arrays.toString(this.dis));
    }
}
