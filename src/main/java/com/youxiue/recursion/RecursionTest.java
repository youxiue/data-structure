package com.youxiue.recursion;

import org.junit.Test;

public class RecursionTest {

    @Test
    public void main() {
//        test(5);
        int factorial = factorial(5);
        System.out.println("阶乘结果:" + factorial);
    }

    // 打印问题
    public void test(int n) {
        if (n > 2) {
            test(n - 1);
        }
        System.out.println("n= " + n);
    }

    // 阶乘问题
    public int factorial(int n) {
        if (n == 1) {
            return n;
        } else {
            return (factorial(n - 1) * n);
        }
    }


}
