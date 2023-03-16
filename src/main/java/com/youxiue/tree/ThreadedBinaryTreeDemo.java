package com.youxiue.tree;

import org.junit.Test;

/**
 * Created by xfb on 2020/07/25 20:54.
 * 线索二叉树
 */
public class ThreadedBinaryTreeDemo {

    @Test
    public void test() {
        ThreadedBinaryTree binaryTree = new ThreadedBinaryTree();
        ThreadedHero root = new ThreadedHero(1, "大乔"); // 美国绅士
        ThreadedHero node2 = new ThreadedHero(2, "二乔"); // 法国臭流氓
        ThreadedHero node3 = new ThreadedHero(3, "三乔"); // 无敌高中生
        ThreadedHero node4 = new ThreadedHero(4, "四乔"); // 飞机头少年
        ThreadedHero node5 = new ThreadedHero(5, "五乔"); // 意大利秧歌Star
        ThreadedHero node6 = new ThreadedHero(6, "六乔"); //

        root.setLeft(node2);
        root.setRight(node5);
        node2.setLeft(node3);
        node2.setRight(node4);
        node5.setLeft(node6);

        node3.setParent(node2);
        node4.setParent(node2);
        node2.setParent(root);
        node6.setParent(node5);
        node5.setParent(root);

        binaryTree.setRoot(root);

        //         1
        //    2        5
        // 3    4    6
        // 中序结果为 3 2 4 1 6 5
        // 前序结果为 1 2 3 4 5 6

        // 线索化
        //binaryTree.threadedNodes(root);
        //binaryTree.threadedOrder();
        //binaryTree.preThreadedNodes(root);
        //binaryTree.preThreadedOrder();

        binaryTree.postThreadedNodes(root);
        binaryTree.postThreadedOrder();

        // 查看 node 的左右指向 是否线索化
        /*ThreadedHero right = node3.getRight();
        ThreadedHero left = node3.getLeft();
        System.out.println("left:" + left);
        System.out.println("right:" + right);*/


    }

}

class ThreadedBinaryTree {
    private ThreadedHero root;
    private ThreadedHero pre;

    public void setRoot(ThreadedHero root) {
        this.root = root;
    }

    // 前序线索树遍历
    public void preThreadedOrder() {
        ThreadedHero node = root;

        while (node != null) {
            System.out.println(node);

            while (node.getLeftType() != 1) {
                node = node.getLeft();
                System.out.println(node);
            }

            while (node.getRightType() == 1) {
                node = node.getRight();
                System.out.println(node);
            }
            node = node.getRight();
        }
    }

    // 中序线索树遍历
    public void threadedOrder() {
        ThreadedHero node = root;

        while (node != null) {
            // 一直往左遍历  找到leftType== 1 的节点
            while (node.getLeftType() != 1) {
                node = node.getLeft();
            }
            System.out.println(node);

            // 如果右边为后继节点, 则打印输出
            while (node.getRightType() == 1) {
                node = node.getRight();
                System.out.println(node);
            }
            // 右边不为 后继节点了,  则 将右边赋值为node , 继续整个大循环, 从右边这个节点往左查找遍历
            node = node.getRight();
        }
    }

    // 后序线索树遍历
    public void postThreadedOrder() {

        ThreadedHero node = root;
        //获取到最左边的子节点
        while (node != null && node.getLeftType() != 1) {
            node = node.getLeft();
        }

        while (node != null) {
            // 如果右边是 后继节点
            if (node.getRightType() == 1) {
                System.out.println(node);
                pre = node;
                node = node.getRight();
            } else {
                // 如果右边不是后继节点
                // 判断右边是否 是前驱节点,  如果是的话 则回到父节点
                if (node.getRight() == pre) {
                    System.out.println(node);
                    if (node == root) { // 如果回到了 root 节点 则退出
                        return;
                    }
                    pre = node;
                    node = node.getParent();
                } else {
                    // 如果右边也不是前驱节点
                    node = node.getRight();
                    // 那么就说明没有被遍历过 则查找该节点右子节点下面的最左边的子节点
                    while (node != null && node.getLeftType() != 1) {
                        node = node.getLeft();
                    }
                }
            }
        }


    }

    // 中序 线索化树
    public void threadedNodes(ThreadedHero node) {
        // 节点不存在, 不能线索化
        if (node == null) {
            return;
        }

        // 1. 先线索化左子树
        threadedNodes(node.getLeft());
        // 2. 然后线索化当前节点
        // 2.1 如果当前节点的 左边为空, 则将左边指向 前驱节点
        if (node.getLeft() == null) {
            node.setLeft(pre);
            node.setLeftType(1);
        }
        // 如果前驱节点的 右边为空, 则将前驱节点的右边指向当前节点,  因为当前节点是 前驱节点的后继节点
        if (pre != null && pre.getRight() == null) {
            pre.setRight(node);
            pre.setRightType(1);
        }

        // 序列完当前节点后,  将pre 指向当前节点
        pre = node;

        // 3. 最后线索化右子树
        threadedNodes(node.getRight());
    }


    // 前序 线索化树
    public void preThreadedNodes(ThreadedHero node) {
        // 节点不存在, 不能线索化
        if (node == null) {
            return;
        }

        // 2. 然后线索化当前节点
        // 2.1 如果当前节点的 左边为空, 则将左边指向 前驱节点
        if (node.getLeft() == null) {
            node.setLeft(pre);
            node.setLeftType(1);
        }
        // 如果前驱节点的 右边为空, 则将前驱节点的右边指向当前节点,  因为当前节点是 前驱节点的后继节点
        if (pre != null && pre.getRight() == null) {
            pre.setRight(node);
            pre.setRightType(1);
        }

        // 序列完当前节点后,  将pre 指向当前节点
        pre = node;

        // 1. 先线索化左子树
        if (node.getLeftType() != 1) {
            preThreadedNodes(node.getLeft());
        }

        // 3. 最后线索化右子树
        if (node.getRightType() != 1) {
            preThreadedNodes(node.getRight());
        }

    }

    // 后序 线索化树
    public void postThreadedNodes(ThreadedHero node) {
        // 节点不存在, 不能线索化
        if (node == null) {
            return;
        }


        // 1. 先线索化左子树
        if (node.getLeftType() != 1) {
            postThreadedNodes(node.getLeft());
        }

        // 3. 最后线索化右子树
        if (node.getRightType() != 1) {
            postThreadedNodes(node.getRight());
        }

        // 2. 然后线索化当前节点
        // 2.1 如果当前节点的 左边为空, 则将左边指向 前驱节点
        if (node.getLeft() == null) {
            node.setLeft(pre);
            node.setLeftType(1);
        }
        // 如果前驱节点的 右边为空, 则将前驱节点的右边指向当前节点,  因为当前节点是 前驱节点的后继节点
        if (pre != null && pre.getRight() == null) {
            pre.setRight(node);
            pre.setRightType(1);
        }

        // 序列完当前节点后,  将pre 指向当前节点
        pre = node;


    }

}