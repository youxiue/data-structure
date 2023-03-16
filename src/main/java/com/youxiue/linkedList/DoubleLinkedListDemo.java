package com.youxiue.linkedList;

/**
 * 双向链表
 */
public class DoubleLinkedListDemo {

    public static void main(String[] args) {

        HeroNode2 hero1 = new HeroNode2(1, "京东", "狗东");
        HeroNode2 hero2 = new HeroNode2(2, "天猫", "黑猫");
        HeroNode2 hero3 = new HeroNode2(3, "淘宝", "某宝");
        HeroNode2 hero4 = new HeroNode2(4, "苏宁", "SN");

        DoubleLinkedList list = new DoubleLinkedList();

        /*System.out.println("添加测试~~~~~~~~~~~~~~~~");
        list.add(hero4);
        list.add(hero2);
        list.add(hero3);
        list.add(hero1);
        list.list();*/
        System.out.println("顺序添加测试~~~~~~~~~~~~~~~~");
        list.orderAdd(hero4);
        list.orderAdd(hero3);
        list.orderAdd(hero2);
        list.orderAdd(hero1);
        list.list();

        System.out.println("修改测试~~~~~~~~~~~~~~~~");
        HeroNode2 updateHero = new HeroNode2(2, "地锚", "傻冒");
        list.update(updateHero);
        list.list();

        System.out.println("删除测试~~~~~~~~~~~~~~~~");
        list.delete(1);
        list.list();
        System.out.println("-----------------");
        list.delete(2);
        list.list();
        System.out.println("-----------------");
        list.delete(4);
        list.list();
        System.out.println("-----------------");
        list.delete(3);
        list.list();

    }
}

// 创建一个双向链表类
class DoubleLinkedList {
    // 初始化一个头节点
    private HeroNode2 head = new HeroNode2(0, "", "");

    // 返回头节点
    public HeroNode2 getHead() {
        return head;
    }

    // 添加  插到末尾
    public void add(HeroNode2 heroNode) {

        // 定义一个指针
        HeroNode2 temp = head;
        // 获取到末尾节点
        while (temp.next != null) {
            temp = temp.next;
        }
        // 将节点插入到末尾, 形成双向链表
        temp.next = heroNode;
        heroNode.pre = temp;
    }

    // 按照编号大小 顺序添加
    public void orderAdd(HeroNode2 heroNode) {
        HeroNode2 temp = head;
        // 获取到末尾节点
        while (temp.next != null) {
            // 如果下一个节点的编号大于要插入的节点的编号了  则推出
            if (temp.next.no > heroNode.no) {
                break;
            }
            temp = temp.next;
        }
        // 插入节点
        if (temp.next != null) {
            // 建立 temp.next和 heroNode的关系
            heroNode.next = temp.next;
            temp.next.pre = heroNode;
            // 建立 heroNode 和 temp 的关系
            heroNode.pre = temp;
            temp.next = heroNode;

        } else {
            temp.next = heroNode;
            heroNode.pre = temp;
        }

    }

    //根据编号修改节点
    public void update(HeroNode2 heroNode) {
        if (head.next == null) {
            System.out.println("链表为空！");
            return;
        }
        // 定义一个指针
        HeroNode2 temp = head.next;
        boolean flag = false;

        while (temp != null) {
            if (temp.no == heroNode.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }

        if (flag) {
            temp.name = heroNode.name;
            temp.nickname = heroNode.nickname;
        } else {
            System.out.printf("没有找到 编号%d 的英雄", heroNode.no);
        }
    }

    // 删除 节点
    public void delete(int no) {
        if (head.next == null) {
            System.out.println("链表为空！");
            return;
        }
        // 定义一个指针
        HeroNode2 temp = head.next;
        boolean flag = false;

        while (temp != null) {
            if (temp.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }

        // 删除该节点
        if (flag) {
            temp.pre.next = temp.next;
            // 如果temp 不是最后一个节点
            if (temp.next != null) {
                temp.next.pre = temp.pre;
            }

        } else {
            System.out.printf("没有找到 编号%d 的节点", no);
        }

    }

    // 遍历双向链表 打印输出
    public void list() {
        if (head.next == null) {
            System.out.println("链表为空！");
            return;
        }
        // 定义一个指针
        HeroNode2 temp = head.next;
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }
    }
}

// 定义HeroNode ， 每一个HeroNode 就是一个节点
class HeroNode2 {
    public int no;// 编号
    public String name;// 名称
    public String nickname;// 昵称
    public HeroNode2 next;// 指向下一个节点 默认为null
    public HeroNode2 pre;// 指向上一个节点， 默认为null

    public HeroNode2(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "com.youxiue.linkedList.HeroNode2{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}