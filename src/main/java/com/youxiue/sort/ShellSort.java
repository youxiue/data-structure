package com.youxiue.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 希尔排序
 * 间隔大的时候, 移动次数少
 * 间隔小的时候, 移动距离短
 * 800000个数字排序时间为 1s不到
 * Created by xfb on 2020/07/04 23:17.
 */
public class ShellSort {
    public static void main(String[] args) {
        //int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        int[] arr = new int[800000];
        for (int i = 0; i < 800000; i++) {
            //生成 [0 8000000)的数
            arr[i] = (int) (Math.random() * 8000000);
        }
        SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.printf("开始时间:%s \n", simple.format(new Date()));
        shellInsertSort(arr);
        System.out.printf("结束时间:%s \n", simple.format(new Date()));
        //shellSort(arr);
        //shellSelectSort(arr);
        //shellInsertSort(arr);
    }

    // 推导 过程
    public static void shellSort(int[] arr) {
        // 希尔排序的第1轮排序
        int temp;
        // 第一轮排序, 是将10个数据分成了5组
        for (int i = 5; i < arr.length; i++) {
            // 将每组数进行排序
            for (int j = i - 5; j >= 0; j -= 5) {
                if (arr[j] > arr[j + 5]) {
                    temp = arr[j];
                    arr[j] = arr[j + 5];
                    arr[j + 5] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(arr)); // [3, 5, 1, 6, 0, 8, 9, 4, 7, 2]

        // 第二轮排序 , 将 10个数分成 5/2 = 2组
        for (int i = 2; i < arr.length; i++) {
            // 将每组数进行排序
            for (int j = i - 2; j >= 0; j -= 2) {
                if (arr[j] > arr[j + 2]) {
                    temp = arr[j];
                    arr[j] = arr[j + 2];
                    arr[j + 2] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(arr)); //[0, 2, 1, 4, 3, 5, 7, 6, 9, 8]

        // 第三轮排序, 将 10个数分成 2/2 = 1组
        for (int i = 1; i < arr.length; i++) {
            // 将每组数进行排序
            for (int j = i - 1; j >= 0; j -= 1) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(arr)); //[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
    }

    /**
     * 希尔排序  交换方式是选择排序(慢)
     *
     * @param arr
     */
    public static void shellSelectSort(int[] arr) {
        int temp;
        // 循环分组
        for (int step = arr.length / 2; step >= 1; step /= 2) {
            // 循环每一组
            for (int i = step; i < arr.length; i++) {
                // 循环组中的每一个元素
                for (int j = i - step; j >= 0; j -= step) {
                    // 如果组中的前一个数据比后一个大, 则 交换位置
                    if (arr[j] > arr[j + step]) {
                        temp = arr[j];
                        arr[j] = arr[j + step];
                        arr[j + step] = temp;
                    }
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 希尔排序, 交换方式是 插入排序 (快)
     *
     * @param arr
     */
    public static void shellInsertSort(int[] arr) {
        int temp;
        // 循环分组
        for (int step = arr.length / 2; step >= 1; step /= 2) {
            // 循环每一组
            for (int i = step; i < arr.length; i++) {

                int index = i - step; // 前面有序数组的 最后一个下标
                int value = arr[i]; // 当前值
                // 将当前值与 前面有序数组的每一个值进行循环比较, 如果比当前值大, 则有序数组值后移一位, 前面空出一个空位方面插入
                while (index >= 0 && value < arr[index]) {
                    arr[index + step] = arr[index];
                    index -= step;
                }
                // 当碰到没有当前值大的数时,  将当前值插入.
                arr[index + step] = value;
            }
        }
        //System.out.println(Arrays.toString(arr));
    }

}
