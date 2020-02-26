package stack;

import java.util.Scanner;

/**
 * 手动实现栈
 */
public class ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack arrayStack = new ArrayStack(5);
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        String key = "";
        while (loop){
            System.out.println("s:表示显示栈");
            System.out.println("e:退出");
            System.out.println("p:添加数据到栈");
            System.out.println("t:从栈取出数据");
            System.out.println("请输入你的选择：");
            key = scanner.next();
            switch (key){
                case "s":
                    arrayStack.list();
                    break;
                case "e":
                    loop = false;
                    break;
                case "p":
                    System.out.println("请输入要添加的数字：");
                    int i = scanner.nextInt();
                    arrayStack.push(i);
                    break;
                case "t":
                    try {
                        System.out.println("出栈数据是："+arrayStack.pop());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    System.out.println("请输入正确选项！");
                    break;
            }
        }
    }
}

// 定义一个ArrayStack ， 表示栈
class ArrayStack {
    private int maxSize;
    private int[] stack;// 数组， 数组模拟栈， 数据就放在该数组
    private int top = -1; // 栈顶， 初始化为-1

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        this.stack = new int[maxSize];
    }

    public boolean isFull() {
        return top == maxSize - 1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    // 入栈
    public void push(int value){
        if(isFull()){
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = value;
    }

    // 出栈
    public int pop(){
        if(isEmpty()){
            throw new RuntimeException("栈为空");
        }
        int value =stack[top];
        top--;
        return value;
    }

    // 遍历
    public void list(){
        if(isEmpty()){
            System.out.println("栈空");
            return;
        }
        //从栈顶开始遍历
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n",i,stack[i]);
        }
    }


}