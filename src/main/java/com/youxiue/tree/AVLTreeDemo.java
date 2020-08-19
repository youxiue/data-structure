package com.youxiue.tree;

import org.junit.Test;

/**
 * Created by xfb on 2020/08/02 18:44.
 * 平衡二叉树
 */
public class AVLTreeDemo {
    @Test
    public void test() {
        //int[] arr = {4, 3, 6, 5, 7, 8};
        //int[] arr = {7, 3, 6, 5, 4, 8};
        int[] arr = {10, 11, 7, 6, 8, 9};
        AvlTree avlTree = new AvlTree();
        for (int i = 0; i < arr.length; i++) {
            AvlNode node = new AvlNode(arr[i]);
            avlTree.add(node);
        }
        avlTree.infixOrder();
        System.out.println(avlTree.root.value);
        System.out.println(avlTree.root.leftHeight());
        System.out.println(avlTree.root.rightHeight());

    }

}

class AvlTree {
    AvlNode root;

    public void printRoot() {
        if (root == null) {
            System.out.println("root为空");
        } else {
            System.out.println("root: " + root.value);
        }
    }

    // 添加方法
    public void add(AvlNode node) {
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

    public AvlNode search(int value) {
        if (this.root == null) {
            System.out.println("root为空");
            return null;
        }
        AvlNode search = this.root.search(value);
        if (search != null) {
            return search;
        }
        System.out.println("节点没找到");
        return null;
    }

    public AvlNode searchParent(int value) {
        if (this.root == null) {
            System.out.println("root为空");
            return null;
        }
        AvlNode searchParent = this.root.searchParent(value);
        if (searchParent != null) {
            return searchParent;
        }
        System.out.println("父节点没找到");
        return null;
    }

    // 删除节点
    public void delAvlNode(int value) {
        //查找当前节点
        AvlNode node = this.search(value);
        if (node == null) {
            return;
        }
        AvlNode parentAvlNode = this.searchParent(value);
        if (node.left == null && node.right == null) {
            // 如果当前节点是叶子节点
            if (parentAvlNode == null) {
                // 如果没有有父节点 , 则 说明 该节点是root 节点
                this.root = null;
            } else {
                // 如果有父节点, 则判断是左子节点 还是右子节点
                if (parentAvlNode.left == node) {
                    parentAvlNode.left = null;
                } else {
                    parentAvlNode.right = null;
                }
            }
        } else if (node.left != null && node.right != null) {
            // 如果是父节点的左节点, 找到 parentAvlNode 左子节点的最大值,删除该节点并返回对应的值
            node.value = delBigAvlNode(node.left);
        } else {
            // 如果当前节点 只有一个子节点
            if (parentAvlNode != null) {
                // 如果父节点 不为空
                if (parentAvlNode.left == node) {
                    // 如果是父节点的左节点, 将node 的唯一一个子节点给父
                    if (node.left != null) {
                        parentAvlNode.left = node.left;
                    } else {
                        parentAvlNode.left = node.right;
                    }

                } else {
                    // 如果node 是父节点的右节点, 将node的节点给父
                    if (node.left != null) {
                        parentAvlNode.right = node.left;
                    } else {
                        parentAvlNode.right = node.right;
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
    public int delBigAvlNode(AvlNode node) {
        AvlNode target = node;
        while (target.right != null) {
            target = target.right;
        }
        delAvlNode(target.value);
        return target.value;
    }


}


class AvlNode {
    int value;
    AvlNode left;
    AvlNode right;

    public AvlNode(int value) {
        this.value = value;
    }

    public int leftHeight() {
        if (this.left == null) {
            return 0;
        }
        return this.left.getHeight();
    }

    public int rightHeight() {
        if (this.right == null) {
            return 0;
        }
        return this.right.getHeight();
    }

    // 获取节点高度
    public int getHeight() {
        return Math.max(this.left == null ? 0 : this.left.getHeight(), this.right == null ? 0 : this.right.getHeight()) + 1;
    }

    // 查找当前节点的方法
    public AvlNode search(int value) {
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
    public AvlNode searchParent(int value) {
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
    void add(AvlNode node) {
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
        if (rightHeight() - leftHeight() > 1) {
            // 如果
            if (right != null && right.leftHeight() > right.rightHeight()) {
                this.right.rightAvl();
            }
            leftAvl();
        }
        // 左边树的 比右边树的高度差 大于 1
        if (leftHeight() - rightHeight() > 1) {
            // 如果左子树的右子树高度比左子树的左子树高度高, 则先对左子树进行左旋转
            if (left != null && left.rightHeight() > left.leftHeight()) {
                this.left.leftAvl();
            }
            rightAvl();
        }
    }

    // 左旋转方法
    void leftAvl() {
        // 创建一个新的节点, 值为当前节点的值
        AvlNode avlNode = new AvlNode(this.value);
        // 将新节点的左节点 指向当前节点的左节点
        avlNode.left = this.left;
        // 将新节点的右节点, 指向 当前节点的 右节点的左节点
        avlNode.right = this.right.left;
        // 将当前节点的值设置为当前节点的右节点的值
        this.value = this.right.value;
        // 将当前节点的右节点指向当前节点的右节点的右节点
        this.right = this.right.right;
        // 将当前节点的左节点 指向新的节点
        this.left = avlNode;
    }

    // 右旋转
    void rightAvl() {
        AvlNode avlNode = new AvlNode(this.value);
        avlNode.right = this.right;
        avlNode.left = this.left.right;
        this.value = this.left.value;
        this.left = this.left.left;
        this.right = avlNode;
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
