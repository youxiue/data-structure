package com.youxiue.tree;

import lombok.Data;

/**
 * Created by xfb on 2020/07/21 21:36.
 */
@Data
public class Hero {

    public Integer no;

    public String name;

    public Hero left;

    public Hero right;

    public Hero(Integer no, String name) {
        this.no = no;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Hero{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    /**
     * 前序遍历
     */
    public void preOrder() {
        // 先输出父节点
        System.out.println(this);
        // 然后 向左遍历
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    /**
     * 中序遍历
     */
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    /**
     * 后序遍历
     */
    public void postOrder() {
        if (this.left != null) {
            this.left.postOrder();
        }
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);
    }

    /**
     * 前序查找
     */
    public Hero preOrderSearch(int no) {
        System.out.println("前序查找" + this.no);
        if (this.no == no) {
            return this;
        }
        Hero hero = null;
        if (this.left != null) {
            hero = this.left.preOrderSearch(no);
        }
        if (hero != null) {
            return hero;
        }

        if (this.right != null) {
            hero = this.right.preOrderSearch(no);
        }
        return hero;
    }

    /**
     * 中序查找
     */
    public Hero infixOrderSearch(int no) {

        Hero hero = null;
        if (this.left != null) {
            hero = this.left.infixOrderSearch(no);
        }
        if (hero != null) {
            return hero;
        }
        System.out.println("中序查找" + this.no);
        if (this.no == no) {
            return this;
        }
        if (this.right != null) {
            hero = this.right.infixOrderSearch(no);
        }
        return hero;
    }

    /**
     * 后序查找
     */
    public Hero postOrderSearch(int no) {

        Hero hero = null;
        if (this.left != null) {
            hero = this.left.postOrderSearch(no);
        }
        if (hero != null) {
            return hero;
        }
        if (this.right != null) {
            hero = this.right.postOrderSearch(no);
        }
        if (hero != null) {
            return hero;
        }
        System.out.println("后序查找" + this.no);
        if (this.no == no) {
            return this;
        }
        return hero;
    }

    /**
     * 查找删除
     */
    public void delNode(int no, boolean flag) {
        // 1. 先判断 左子节点是否为空, 如果不为空, 则判断是否为 目标节点
        if (this.left != null && this.left.no == no) {
            this.left = null;
            flag = true;
            return;
        }
        // 2. 再判断 右子节点是否为空, 如果不为空, 则判断是否为 目标节点
        if (this.right != null && this.right.no == no) {
            this.right = null;
            flag = true;
            return;
        }
        // 3. 如果左右子节点都不是要找的,  则从左子节点往下遍历 查找
        if (this.left != null && !flag) {
            this.left.delNode(no, flag);
        }

        // 4.如果没有找到 , 则从右子节点往下遍历 查找
        if (this.right != null && flag) {
            this.right.delNode(no, flag);
        }

    }
}
