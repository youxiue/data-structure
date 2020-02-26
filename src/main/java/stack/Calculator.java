package stack;


/**
 * 使用自定义 栈 计算中缀表达式
 * 存在bug
 */
public class Calculator {
    public static void main(String[] args) {
        String expression = "700+20*6-4+5*3";

        // 1.定义一个变量 用来遍历 计算式， 定义数字栈 和 符号栈
        ArrayStack2 numStack = new ArrayStack2(100);
        ArrayStack2 operStack = new ArrayStack2(100);
        int index = 0;
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' ';
        String keepNum = "";
        while (true) {

            ch = expression.substring(index, index + 1).charAt(0);
            if (operStack.isOper(ch)) {
                // 3.如果是符号的话
                // 3.1 判断符号栈里是否已经存在符号
                if (operStack.isEmpty()) {
                    // 3.1.1 如果不存在符号则直接存入
                    operStack.push(ch);
                } else {
                    // 3.1.2 如果存在符号则取出一个符号，比较优先级
                    if (operStack.priority(ch) < operStack.priority(operStack.peek())) {
                        // 3.1.2.1 如果外面的符号优先级比 栈中的优先级低,
                        // 则 从数字栈中取出两个数字,从符号栈中取出一个符号, 进行运算后 将运行的结果存入数字栈,将外面的符号存入符号栈
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.count(num1, num2, oper);
                        numStack.push(res);
                        operStack.push(ch);

                    } else {
                        // 3.1.2.2 如果外面的符号的优先级比 栈中的优先级高, 则直接将符号存入到栈中
                        operStack.push(ch);
                    }
                }


            } else {
                // 2.如果是数字的话， 则直接 存入数字栈
                //numStack.push(ch - 48);

                // 如果是数字的话 判断下一位是否位数字

                keepNum += ch;
                // 判断当前是否为最后一位
                if (index == expression.length() - 1) {
                    // 如果是则 直接入栈
                    numStack.push(Integer.parseInt(keepNum));
                } else {
                    // 如果不是
                    // 先判断是否为 字符串
                    if (operStack.isOper(ch = expression.substring(index + 1, index + 2).charAt(0))) {
                        // 如果后一位是运算符, 则 入栈keepNum
                        numStack.push(Integer.parseInt(keepNum));
                        keepNum = "";
                    }
                }
            }
            index++;
            if (index == expression.length()) {
                break;
            }
        }

        // 4. 遍历栈
        while (true) {
            if (operStack.isEmpty()) {
                break;
            }
            // 4.1 取出两个数字和一个符号, 运算之后将结果压入到数字栈,
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.count(num1, num2, oper);
            numStack.push(res);
        }
        // 5. 取出栈底的 数字即为计算结果
        System.out.println("计算结果为: " + numStack.pop());
    }


}

// 定义一个ArrayStack ， 表示栈
class ArrayStack2 {
    private int maxSize;
    private int[] stack;// 数组， 数组模拟栈， 数据就放在该数组
    private int top = -1; // 栈顶， 初始化为-1

    public ArrayStack2(int maxSize) {
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
    public void push(int value) {
        if (isFull()) {
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = value;
    }

    public int peek() {
        if (isEmpty()) {
            throw new RuntimeException("栈为空");
        }
        return stack[top];
    }


    // 出栈
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈为空");
        }
        int value = stack[top];
        top--;
        return value;
    }

    // 遍历
    public void list() {
        if (isEmpty()) {
            System.out.println("栈空");
            return;
        }
        //从栈顶开始遍历
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }

    //判断是否是运算符
    public boolean isOper(int oper) {
        return oper == '+' || oper == '-' || oper == '*' || oper == '/';
    }

    // 返回优先级别
    public int priority(int oper) {
        if (oper == '+' || oper == '-') {
            return 1;
        } else if (oper == '*' || oper == '/') {
            return 2;
        } else {
            return -1;
        }
    }

    // 计算结果
    public int count(int num1, int num2, int oper) {
        switch (oper) {
            case '+':
                return num2 + num1;
            case '-':
                return num2 - num1;
            case '*':
                return num2 * num1;
            case '/':
                return num2 / num1;
            default:
                return 0;
        }
    }

}