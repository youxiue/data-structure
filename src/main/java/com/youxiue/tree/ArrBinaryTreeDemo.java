package com.youxiue.tree;

import org.junit.Test;

/**
 * Created by xfb on 2020/07/25 19:55.
 * 顺序存储二叉树
 * 顺序存储二叉树通常只考虑完全二叉树
 * 第n个元素的左子节点为 2*n+1
 * 第n个元素的右子节点为 2*n+2
 * 第n个元素的父节点为 (n-1)/2
 */
public class ArrBinaryTreeDemo {

    @Test
    public void test() {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
        arrBinaryTree.preOrder(0);
    }
}

class ArrBinaryTree {

    private int[] arr;

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }

    public void preOrder(int index) {
        if (this.arr == null || this.arr.length <= 0) {
            System.out.println("数组为空");
            return;
        }
        System.out.println(arr[index]);
        // 先往左
        if (index * 2 + 1 < arr.length) {
            preOrder(index * 2 + 1);
        }
        // 然后往右
        if (index * 2 + 2 < arr.length) {
            preOrder(index * 2 + 2);
        }
    }

}