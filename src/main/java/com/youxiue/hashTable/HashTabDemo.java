package com.youxiue.hashTable;

import java.util.Scanner;

/**
 * Created by xfb on 2020/07/19 18:15.
 */
public class HashTabDemo {

    public static void main(String[] args) {
        HashTab hashTab = new HashTab(7);
        Scanner scanner = new Scanner(System.in);
        String key = "";
        int id;
        while (true) {
            System.out.println("add: 添加");
            System.out.println("list: 遍历");
            System.out.println("find: 查找");
            System.out.println("remove: 删除");
            System.out.println("exit: 退出");
            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("输入ID");
                    id = scanner.nextInt();
                    System.out.println("输入名字");
                    String name = scanner.next();
                    Emp emp = new Emp(id, name);
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("输入查找ID");
                    id = scanner.nextInt();
                    hashTab.findByID(id);
                    break;
                case "remove":
                    System.out.println("输入删除ID");
                    id = scanner.nextInt();
                    hashTab.remove(id);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    break;
            }

        }
    }
}

class HashTab {
    private int size;

    private EmpLikedList empLinkedListArr[];

    public HashTab(int size) {
        this.size = size;
        this.empLinkedListArr = new EmpLikedList[size];
        // 循环创建 hash 表中的每条 链表
        for (int i = 0; i < size; i++) {
            empLinkedListArr[i] = new EmpLikedList();
        }
    }

    public void add(Emp emp) {
        int k = emp.id % size;
        EmpLikedList empLikedList = empLinkedListArr[k];
        empLikedList.add(emp);
    }

    public void list() {
        for (int i = 0; i < size; i++) {
            empLinkedListArr[i].list(i);
        }
    }

    public void findByID(int id) {
        int k = id % size;
        Emp emp = empLinkedListArr[k].findById(id);
        if (emp != null) {
            System.out.printf("在第 %d 条链表找到: id为 %d, name为 %s\n", k, emp.id, emp.name);
        } else {
            System.out.println("未找到");
        }
    }

    public void remove(int id) {
        int k = id % size;
        empLinkedListArr[k].remove(id);

    }
}


/**
 * 链表
 */
class EmpLikedList {
    private Emp head;

    // 添加方法
    public void add(Emp emp) {
        if (head == null) {
            head = emp;
            return;
        }
        // 定位到链表的末尾
        Emp temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        // 将emp 添加到链表的末尾
        temp.next = emp;
    }

    // 遍历方法
    public void list(int no) {
        if (head == null) {
            System.out.println("第" + no + "链表为空");
            return;
        }
        System.out.println("第" + no + "链表:");
        Emp temp = head;
        while (true) {
            System.out.printf("=> id: %d, name: %s \n", temp.id, temp.name);
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
    }

    public Emp findById(int id) {
        if (head == null) {
            System.out.println("链表为空!");
            return null;
        }
        Emp temp = head;
        while (true) {
            if (temp.id == id) {
                return temp;
            }
            if (temp.next == null) {
                return null;
            }
            temp = temp.next;
        }
    }

    public void remove(int id) {
        if (head == null) {
            System.out.println("链表为空!");
            return;
        }
        Emp temp = head;
        Emp tempNext = temp.next;
        if (head.id == id) {
            head = tempNext;
            return;
        }
        while (true) {
            if (tempNext == null) {
                return;
            }
            if (tempNext.id == id) {
                temp.next = tempNext.next;
                return;
            }
            temp = tempNext;
            tempNext = tempNext.next;
        }
    }

}

class Emp {
    public int id;

    public String name;

    public Emp next;

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }
}