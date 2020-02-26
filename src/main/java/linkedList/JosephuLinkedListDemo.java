package linkedList;

import com.sun.xml.internal.ws.wsdl.writer.document.soap.Body;

/**
 * 环形链表
 */
public class JosephuLinkedListDemo {
    public static void main(String[] args) {
        CircleSingleLinkedList linkedList = new CircleSingleLinkedList();
//        linkedList.add(1);
//        linkedList.list();
        linkedList.cq(1,10,3);
    }
}

class CircleSingleLinkedList {
    // 创建一个first 节点
    private Boy first = null;

    //添加方法
    public void add(int nums) {
        if (nums < 1) {
            System.out.println("nums的值不能小于1");
            return;
        }

        Boy curBoy = first;

        for (int i = 1; i <= nums; i++) {
            if (i == 1) {
                first = new Boy(1);
                first.setNext(first);
                curBoy = first;
            }else{
                Boy boy = new Boy(i);
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = curBoy.getNext();
            }
        }

    }

    public void list() {
        Boy curBoy = first;
        while (true) {
            System.out.printf("节点编号：%d", curBoy.getNo());
            if(curBoy.getNext() == first){
                break;
            }
            curBoy = curBoy.getNext();
        }

    }

    /**
     * josepho 问题，小孩出圈
     * @param startNo 起始报数编号
     * @param nums  小孩数量
     * @param m 每次报数几个
     */
    public void cq(int startNo,int nums,int m){
        if(startNo < 1 ||  startNo> nums || m<1){
            System.out.println("参数有误！");
            return;
        }
        add(nums);
        // 定义一个 helper指针，
        Boy helper = first;
        // 需要先定位到 起始位置
        for (int i = 0; i < startNo - 1; i++) {
            first = first.getNext();
        }
        // 将helper 指向first 的后一个节点 5
        while (helper.getNext() != first) {
            helper = helper.getNext();
        }

        // 遍历 链表  循环踢出小孩
        while (helper != first) {
            // 移动到最后一个报数位置
            for (int i = 0; i < m - 1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            // 将该报数小孩踢出 ( 指针后移一位， helper的next 指向移动后的first)
            System.out.println("踢出小孩：" + first.getNo());
            first = first.getNext();
            helper.setNext(first);
        }

        System.out.println("剩下的最后一个小屁孩："+ first.getNo());
    }
}


class Boy {
    private int no;

    private Boy next;

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Boy{" +
                "no=" + no +
                '}';
    }
}
