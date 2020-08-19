package com.youxiue.tree;

import org.junit.Test;

/**
 * Created by xfb on 2020/08/02 10:54.
 * 二叉排序树
 */
public class BinarySortTreeDemo {


    @Test
    public void test() {
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
        BinarySortTree binarySortTree = new BinarySortTree();
        for (int i = 0; i < arr.length; i++) {
            Node node = new Node(arr[i]);
            binarySortTree.add(node);
        }

        //binarySortTree.infixOrder();
        //Node search = binarySortTree.search(3);
        //System.out.println(search.value);

        //Node searchParent = binarySortTree.searchParent(2);
        //System.out.println(searchParent.value);

        binarySortTree.delNode(12);
        binarySortTree.delNode(3);
        binarySortTree.delNode(10);
        binarySortTree.delNode(5);
        binarySortTree.delNode(7);
        binarySortTree.delNode(2);
        binarySortTree.delNode(1);
        binarySortTree.delNode(9);

        binarySortTree.infixOrder();
        binarySortTree.printRoot();
    }

}

class BinarySortTree {
    Node root;

    public void printRoot(){
        if (root == null) {
            System.out.println("root为空");
        } else {
            System.out.println("root: "+root.value);
        }
    }

    // 添加方法
    public void add(Node node) {
        if (node == null) {
            System.out.println("node为空");
            return;
        }
        if (root == null) {
            this.root = node;
        } else {
            this.root.add(node);
        }
    }

    // 中序遍历
    public void infixOrder() {
        if (this.root == null) {
            System.out.println("前序遍历root为空");
            return;
        }
        this.root.infixOrder();
    }

    public Node search(int value) {
        if (this.root == null) {
            System.out.println("root为空");
            return null;
        }
        Node search = this.root.search(value);
        if (search != null) {
            return search;
        }
        System.out.println("节点没找到");
        return null;
    }

    public Node searchParent(int value) {
        if (this.root == null) {
            System.out.println("root为空");
            return null;
        }
        Node searchParent = this.root.searchParent(value);
        if (searchParent != null) {
            return searchParent;
        }
        System.out.println("父节点没找到");
        return null;
    }

    // 删除节点
    public void delNode(int value) {
        //查找当前节点
        Node node = this.search(value);
        if (node == null) {
            return;
        }
        Node parentNode = this.searchParent(value);
        if (node.left == null && node.right == null) {
            // 如果当前节点是叶子节点
            if (parentNode == null) {
                // 如果没有有父节点 , 则 说明 该节点是root 节点
                this.root = null;
            } else {
                // 如果有父节点, 则判断是左子节点 还是右子节点
                if (parentNode.left == node) {
                    parentNode.left = null;
                } else {
                    parentNode.right = null;
                }
            }
        } else if (node.left != null && node.right != null) {
            // 如果是父节点的左节点, 找到 parentNode 左子节点的最大值,删除该节点并返回对应的值
            node.value = delBigNode(node.left);
        } else {
            // 如果当前节点 只有一个子节点
            if (parentNode != null) {
                // 如果父节点 不为空
                if (parentNode.left == node) {
                    // 如果是父节点的左节点, 将node 的唯一一个子节点给父
                    if (node.left != null) {
                        parentNode.left = node.left;
                    } else {
                        parentNode.left = node.right;
                    }

                } else {
                    // 如果node 是父节点的右节点, 将node的节点给父
                    if (node.left != null) {
                        parentNode.right = node.left;
                    } else {
                        parentNode.right = node.right;
                    }
                }
            } else {
                // 如果父节点为空
                if (node.left != null) {
                    this.root = node.left;
                } else {
                    this.root = node.right;
                }
            }
        }

    }

    // 删除节点
    public int delBigNode(Node node) {
        Node target = node;
        while (target.right != null) {
            target = target.right;
        }
        delNode(target.value);
        return target.value;
    }

}

class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    // 查找当前节点的方法
    public Node search(int value) {
        if (this.value == value) {
            return this;
        }
        if (value < this.value && this.left != null) {
            return this.left.search(value);
        } else if (this.value < value && this.right != null) {
            return this.right.search(value);
        } else {
            return null;
        }
    }

    // 查找父节点的方法
    public Node searchParent(int value) {
        // 如果自己的左子节点 或右子节点的值 == 要找的值,  则当前节点就是要找的子节点的父节点
        if ((this.right != null && this.right.value == value) || (this.left != null && this.left.value == value)) {
            return this;
        }
        if (this.left != null && value < this.value) {
            return this.left.searchParent(value);
        } else if (this.right != null && this.value < value) {
            return this.right.searchParent(value);
        } else {
            return null;
        }
    }

    // 添加方法
    void add(Node node) {
        if (node == null) {
            return;
        }
        // 如果小于当前值, 则往左找空位置插入
        if (node.value < this.value) {
            if (this.left == null) {
                this.left = node;
            } else {
                this.left.add(node);
            }
        } else {
            // 如果大于等于当前值,  则往右找空位置插入
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.add(node);
            }
        }
    }

    // 中序遍历方法
    void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this.value);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }
}