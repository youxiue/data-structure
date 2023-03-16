package com.youxiue.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * Created by xfb on 2020/08/04 21:35.
 */
public class Graph {
    // 定点集合
    private ArrayList<String> vertexList;
    // 图形描述矩阵
    private int[][] edges;
    // 边的数量
    private int numOfEdges;
    //记录是否访问过
    private boolean[] isVisit;

    public Graph(String[] arr) {
        this.vertexList = new ArrayList<>();
        this.vertexList.addAll(Arrays.asList(arr));
        this.edges = new int[vertexList.size()][vertexList.size()];
        numOfEdges = 0;
        this.isVisit = new boolean[vertexList.size()];
    }

    // 添加边
    public void addEdge(int i, int j) {
        this.edges[i][j] = 1;
        this.edges[j][i] = 1;
        this.numOfEdges++;
    }

    // 返回边的数量
    public int getNumOfEdges() {
        return this.numOfEdges;
    }

    public String getVertexByIndex(int index) {
        return this.vertexList.get(index);
    }

    // 打印矩阵
    public void printEdges() {
        for (int i = 0; i < edges.length; i++) {
            System.out.println(Arrays.toString(edges[i]));
        }
    }

    public void bfs() {
        for (int i = 0; i < vertexList.size(); i++) {
            if (!isVisit[i]) {
                bfs(isVisit, i);
            }
        }
    }

    /**
     * 广度优先
     */
    public void bfs(boolean[] isVisit, int index) {
        // 定义队列, 存放 获取到的节点
        LinkedList<Integer> list = new LinkedList<>();
        // 队列中弹出的值 u
        int u, w;

        // 输出当前节点
        System.out.println(getVertexByIndex(index) + "=>");
        // 标记当前节点为已经访问过
        isVisit[index] = true;
        // 将当前节点加入队列
        list.addLast(index);

        // 先查找当前节点的第一个连接点

        // 如果队列中有值
        while (list.size() > 0) {
            // 获取队列中的第一个值  将取到的值 弹出
            u = list.removeFirst();
            w = getFirstNode(u);
            while (w != -1) {
                // 如果找到连接点了
                // 判断链接点是否访问过
                if (!isVisit[w]) {
                    // 如果没有访问过
                    // 输出当前节点
                    System.out.println(getVertexByIndex(w) + "=>");
                    // 标记当前节点为已经访问过
                    isVisit[w] = true;
                    // 将当前节点加入队列
                    list.addLast(w);
                }
                // 继续往下找下一个链接点
                w = getNextNode(u, w);
            }
        }


    }


    /**
     * 深度优先算法
     */
    public void dfs() {
        for (int i = 0; i < this.vertexList.size(); i++) {
            if (!isVisit[i]) {
                dfs(isVisit, i);
            }
        }
    }

    public void dfs(boolean[] isVisit, int index) {
        // 打印输出当前节点
        System.out.println(getVertexByIndex(index) + "-> ");
        isVisit[index] = true;
        // 获取第一个相连的节点
        int v1 = getFirstNode(index);
        while (v1 != -1) {
            if (!isVisit[v1]) {
                // 如果这个节点没有被遍历过 则根据这个节点查找下一个节点
                dfs(isVisit, v1);
            }
            // 如果这一个节点被遍历过了
            v1 = getNextNode(index, v1);

        }
    }

    // 找到第一个连接点
    public int getFirstNode(int index) {
        // 韩顺平老师 这里写的是 i =0,  但是我觉得 i= index 应该更对
        for (int i = 0; i < vertexList.size(); i++) {
            //System.out.println("[" + index + "," + i + "]");
            if (edges[index][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    // 获取下一个链接点
    public int getNextNode(int v1, int v2) {
        for (int i = v2 + 1; i < vertexList.size(); i++) {
            //System.out.println("[" + v1 + "," + i + "]");
            if (edges[v1][i] > 0) {
                return i;
            }
        }
        return -1;

    }
}
