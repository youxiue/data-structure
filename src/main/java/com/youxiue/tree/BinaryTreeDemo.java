package com.youxiue.tree;

import org.junit.Test;

/**
 * 二叉树
 * Created by xfb on 2020/07/21 21:35.
 */
public class BinaryTreeDemo {

    @Test
    public void test() {
        BinaryTree binaryTree = new BinaryTree();
        Hero root = new Hero(1, "大乔"); // 美国绅士
        Hero node2 = new Hero(2, "二乔"); // 法国臭流氓
        Hero node3 = new Hero(3, "三乔"); // 无敌高中生
        Hero node4 = new Hero(4, "四乔"); // 飞机头少年
        Hero node5 = new Hero(5, "五乔"); // 意大利秧歌Star

        root.setLeft(node2);
        root.setRight(node5);
        node2.setLeft(node3);
        node2.setRight(node4);
        binaryTree.setRoot(root);
        //         1
        //    2        5
        // 3    4
        System.out.println("前序遍历");
        binaryTree.preOrder(); // 1 2 3 4 5

        System.out.println("中序遍历");
        binaryTree.infixOrder(); // 3 2 4 1 5

        System.out.println("后序遍历");
        binaryTree.postOrder(); // 3 4 2 5 1
    }

    @Test
    public void test2() {
        BinaryTree binaryTree = new BinaryTree();
        Hero root = new Hero(1, "大乔"); // 美国绅士
        Hero node2 = new Hero(2, "二乔"); // 法国臭流氓
        Hero node3 = new Hero(3, "三乔"); // 无敌高中生
        Hero node4 = new Hero(4, "四乔"); // 飞机头少年
        Hero node5 = new Hero(5, "五乔"); // 意大利秧歌Star

        root.setLeft(node2);
        root.setRight(node5);
        node2.setLeft(node3);
        node2.setRight(node4);
        binaryTree.setRoot(root);
        //         1
        //    2        5
        // 3    4
        System.out.println("前序查找");
        //Hero hero = binaryTree.preOrderSearch(4);// 4

        //System.out.println("中序查找");
        //Hero hero = binaryTree.infixOrderSearch(4);// 3

        System.out.println("后序查找");
        Hero hero = binaryTree.postOrderSearch(4);//  2

        if (hero != null) {
            System.out.println(hero);
        } else {
            System.out.println("没有");
        }
    }


    @Test
    public void test3() {
        BinaryTree binaryTree = new BinaryTree();
        Hero root = new Hero(1, "大乔"); // 美国绅士
        Hero node2 = new Hero(2, "二乔"); // 法国臭流氓
        Hero node3 = new Hero(3, "三乔"); // 无敌高中生
        Hero node4 = new Hero(4, "四乔"); // 飞机头少年
        Hero node5 = new Hero(5, "五乔"); // 意大利秧歌Star

        root.setLeft(node2);
        root.setRight(node5);
        node2.setLeft(node3);
        node2.setRight(node4);
        binaryTree.setRoot(root);
        //         1
        //    2        5
        // 3    4
        System.out.println("删除目标节点");
        binaryTree.delNo(3);
        binaryTree.preOrder();

    }


}

class BinaryTree {
    private Hero root;

    public void setRoot(Hero root) {
        this.root = root;
    }

    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("二叉树为空, 无法遍历");
        }

    }

    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("二叉树为空, 无法遍历");
        }
    }

    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("二叉树为空, 无法遍历");
        }
    }

    public Hero preOrderSearch(int no) {
        if (this.root != null) {
            return this.root.preOrderSearch(no);
        } else {
            System.out.println("二叉树为空, 无法遍历");
            return null;
        }
    }


    public Hero infixOrderSearch(int no) {
        if (this.root != null) {
            return this.root.infixOrderSearch(no);
        } else {
            System.out.println("二叉树为空, 无法遍历");
            return null;
        }
    }


    public Hero postOrderSearch(int no) {
        if (this.root != null) {
            return this.root.postOrderSearch(no);
        } else {
            System.out.println("二叉树为空, 无法遍历");
            return null;
        }
    }

    // 删除节点
    public void delNo(int no) {
        if (this.root != null) {
            if (this.root.no == no) {
                this.root = null;
            } else {
                this.root.delNode(no, false);
            }
        } else {
            System.out.println("树是空树!");
        }
    }
}
