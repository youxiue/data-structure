package com.youxiue.stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 逆波兰计算器
 * 计算 后缀表达式
 */
public class PolandNotation {
    public static void main(String[] args) {

        String expression = "1+((2+3)*4)-5+5";
        List<String> infixExpressionList = toInfixExpressionList(expression);
        System.out.println("中缀表达式:" + infixExpressionList);
        List<String> suffixExpressionList = parseSuffixExpressionList(infixExpressionList);
        System.out.println("后缀表达式:" + suffixExpressionList);
        int value = caculate(suffixExpressionList);
        System.out.printf("计算结果为:%d", value);

        /**
         * 先定义逆波兰表达式
         * (3+4)*5-6  => 3 4 + 5 * 6 -
         * 为了方便使用, 逆波兰表达式的数字和符号 使用空格隔开.
         */
//        String suffixExpression = "3 4 + 5 * 6 - ";
        // 4*5-8+60+8/2 => 4 5 * 8 - 60 + 8 2 / +
        /*String suffixExpression = "4 5 * 8 - 60 + 8 2 / +";
        List<String> list = getListString(suffixExpression);
        int k = caculate(list);
        System.out.printf("计算结果为:%d", k);*/
    }

    // 将中缀表达式转为后缀表达式
    public static List<String> parseSuffixExpressionList(List<String> ls) {
        // 先定义两个栈  一个是符号栈s1 , 转换栈s2 , 由于s2 没有pop 操作, 所以这里 使用list来简化
        Stack<String> s1 = new Stack<>();
        ArrayList<String> s2 = new ArrayList<>();

        // 然后遍历中缀表达式
        for (String s : ls) {

            if (s.matches("\\d+")) {
                // 如果是数字 则压入到s2
                s2.add(s);
            } else if (s.equals("(")) {
                // 如果是 左括号 ,则压入s1
                s1.push(s);
            } else if (s.equals(")")) {
                // 如果是右括号, 则将s1 中符号取出 并压入到s2, 知道碰到 左括号, 然后将 左右括号丢弃
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                s1.pop(); // 将左括号丢弃
            } else {
                // 如果是运算符
                // 1 .如果s1是空 ,或栈顶是 (  ,则将运算符压入

                if (s1.isEmpty() || s1.peek().equals("(")) {
                    s1.push(s);
                } else{
                    // 2. 否则 取出s1 栈顶的运算符 压入到s2 , 然后再取出s1 的栈顶运算符进行比较
                    while (!s1.isEmpty() && getPriority(s)<=getPriority(s1.peek())){
                        s2.add(s1.pop());
                    }
                    s1.push(s);
                }
            }
        }

        // 最后将s1中的运算符以此取出 压入到s2
        while (!s1.isEmpty()){
            s2.add(s1.pop());
        }
        return s2;
    }


    // 方法: 将中缀表达式转为对应的List
    public static List<String> toInfixExpressionList(String s) {
        ArrayList<String> list = new ArrayList<>();
        int index = 0;
        String pre = "";
        while (index < s.length()) {
            char c = s.charAt(index);
            // 如果是字符串 则 加入集合

            if (c < 48 || c > 57) {
                list.add(c + "");
            } else {
                // 如果是数字, 则判读下一个是否是数字
                pre += c;
                if (index + 1 >= s.length() || (s.charAt(index + 1) < 48 || s.charAt(index + 1) > 57)) {
                    list.add(pre);
                    pre = "";
                }
            }
            index++;
        }
        return list;
    }

    public static List<String> getListString(String suffixExpression) {
        String[] split = suffixExpression.split(" ");
        return Arrays.asList(split);
    }

    public static int getPriority(String s) {
        switch (s) {
            case "+":
                return 1;
            case "-":
                return 1;
            case "*":
                return 2;
            case "/":
                return 2;
            default:
                throw new RuntimeException("符号异常");
        }
    }

    public static int caculate(List<String> list) {
        Stack<String> stack = new Stack<>();
        int res = 0;
        for (String s : list) {
            if (s.matches("\\d+")) {
                // 如果是数字 则直接压入栈中
                stack.push(s);
            } else {
                // 如果是符号 则取出两个数字进行计算
                int num1 = Integer.parseInt(stack.pop());
                int num2 = Integer.parseInt(stack.pop());
                switch (s) {
                    case "+":
                        res = num2 + num1;
                        break;
                    case "-":
                        res = num2 - num1;
                        break;
                    case "*":
                        res = num2 * num1;
                        break;
                    case "/":
                        res = num2 / num1;
                        break;
                    default:
                        throw new RuntimeException("符号错误");
                }
                stack.push(res + "");
            }
        }

        return Integer.parseInt(stack.pop());
    }
}
