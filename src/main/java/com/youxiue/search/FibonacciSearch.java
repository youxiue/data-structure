package com.youxiue.search;

import org.junit.Test;

import java.util.Arrays;

/**
 * 斐波那契查找算法
 * Created by xfb on 2020/07/19 17:19.
 */
public class FibonacciSearch {

    @Test
    public void test() {
        int[] arr = {1, 8 ,10, 89, 100, 123, 500, 1234};
        int index = fibonacciSearch(arr, 1234);
        System.out.println(index);
    }

    /**
     * 利用斐波那契数组 查询 目标数的下标
     */
    public int fibonacciSearch(int[] arr, int findVal) {
        // 两端下标值
        int low = 0;
        int high = arr.length - 1;
        int mid = 0;

        int k = 0; // 斐波那契数组的下标 索引
        int[] f = fib();// 斐波那契数组

        // 1. 将arr 填充为 比它大一点长度的斐波那契数长度
        // 1.1 获取比arr.length 长一点的 斐波那契数 的 下标
        while (arr.length > f[k] - 1) {
            k++;
        }

        // 1.2 将 arr copy 出一份长度等于斐波那契数 的 数组,  这里 超过arr长度的位置默认填充0
        int[] temp = Arrays.copyOf(arr, f[k] - 1);

        // 1.3 将超过arr长度的位置 填充为 arr[high]
        for (int i = high + 1; i < temp.length; i++) {
            temp[i] = arr[high];
        }


        // 2. 查找对应的数
        while (low <= high) {
            mid = low + f[k - 1] - 1;
            if (temp[mid] > findVal) {
                // 如果目标值 小于 中间值, 说明在 中间值的左边
                // 将 high 左移,
                high = mid - 1;
                k--;
            } else if (temp[mid] < findVal) {
                // 如果目标值 大于 中间值, 说明在 中间值的右边
                // 将 low 右移
                low = mid + 1;
                k -= 2;
            } else {
                //  当 mid > arr.length - 1 时 返回 arr.length - 1
                return Math.min(mid, arr.length - 1);
            }
        }
        return -1;
    }

    /**
     * 返回斐波那契数组
     */
    public int[] fib() {
        int length = 10;
        int[] fib = new int[length];
        fib[0] = 1;
        fib[1] = 1;
        for (int i = 2; i < fib.length; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }
        System.out.println(Arrays.toString(fib));
        return fib;
    }
}
