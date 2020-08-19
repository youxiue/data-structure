package com.youxiue.tree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by xfb on 2020/07/28 22:08.
 * 赫夫曼树
 */
public class HuffmanTreeDemo {

    @Test
    public void test() {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};
        HuffmanNode huffmanTree = createHuffmanTree(arr);

        preOrder(huffmanTree);
    }


    public HuffmanNode createHuffmanTree(int[] arr) {
        // 先将数组转成 节点集合
        ArrayList<HuffmanNode> nodeList = new ArrayList<>();
        for (int i : arr) {
            HuffmanNode node = new HuffmanNode(i);
            nodeList.add(node);
        }

        // 排序
        while (nodeList.size() > 1) {
            Collections.sort(nodeList);

            // 将最小的值进行组合成一个新树
            HuffmanNode leftNode = nodeList.get(0);
            HuffmanNode rightNode = nodeList.get(1);
            HuffmanNode parentNode = new HuffmanNode(rightNode.getValue() + leftNode.getValue());
            parentNode.left = leftNode;
            parentNode.right = rightNode;

            // 将被合并的两个节点 从集合中移除
            nodeList.remove(rightNode);
            nodeList.remove(leftNode);
            nodeList.add(parentNode);
        }

        return nodeList.get(0);

    }


    // 前序遍历
    public void preOrder(HuffmanNode node) {
        System.out.println(node.getValue());
        if (node.left != null) {
            preOrder(node.left);
        }
        if (node.right != null) {
            preOrder(node.right);
        }
    }
}

class HuffmanNode implements Comparable<HuffmanNode> {
    private int value;

    public HuffmanNode left;
    public HuffmanNode right;

    public HuffmanNode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public int compareTo(HuffmanNode node) {
        return this.value - node.value;
    }

    @Override
    public String toString() {
        return "HuffmanNode{" +
                "value=" + value +
                '}';
    }
}