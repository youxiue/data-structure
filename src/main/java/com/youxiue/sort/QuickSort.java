package com.youxiue.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by xfb on 2020/07/05 13:18.
 * 快速排序
 * 平均时间复杂度O(n*LogN)
 * 800000 排序时间不到1秒
 */
public class QuickSort {

    public static void main(String[] args) {
        //int[] arr = {9, 8, 8, 1, 0, 2, 3, 5, 4, 6, 7, 4};
        int[] arr = new int[800000];
        for (int i = 0; i < 800000; i++) {
            //生成 [0 8000000)的数
            arr[i] = (int)(Math.random() * 8000000);
        }
        SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.printf("开始时间:%s \n", simple.format(new Date()));
        //quickSort1(arr, 0, arr.length - 1);
        //quickSort3(arr, 0, arr.length - 1);
        quickSort(arr, 0, arr.length - 1);
        System.out.printf("结束时间:%s \n", simple.format(new Date()));

        //System.out.println(Arrays.toString(arr));
    }

    /**
     * 马士兵老师的版本, 结果正确
     */
    static void quickSort1(int[] arr, int leftBound, int rightBound) {
        if (leftBound >= rightBound) return;
        int pivot = arr[rightBound]; // 以最右边的值为中心值
        int left = leftBound;
        int right = rightBound - 1;

        int temp;
        // 循环
        while (left <= right) {
            while (left <= right && arr[left] <= pivot) {
                left++;
            }
            while (left <= right && arr[right] > pivot) {
                right--;
            }

            // 进行交换
            if (left < right) {
                temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
                System.out.println(temp);
            }
        }
        //System.out.println(left + "->" + right);
        temp = arr[left];
        arr[left] = arr[rightBound];
        arr[rightBound] = temp;
        //System.out.println(Arrays.toString(arr));
        quickSort1(arr, leftBound, left - 1);
        quickSort1(arr, left + 1, rightBound);
    }

    /**
     * 韩顺平老师的版本
     */
    public static void quickSort3(int[] arr, int leftBound, int rightBound) {
        int pivot = arr[(leftBound+rightBound)/2]; // 以最右边的值为中心值
        int left = leftBound;
        int right = rightBound;

        int temp;
        while (left < right) {
            while (arr[left] < pivot) {
                left++;
            }
            while (arr[right] > pivot) {
                right--;
            }

            // 如果经过前面两个 while 循环找到的值 ,他们的左索引比右索引大
            // 则说明左边已经全部都是小于 pivot的值, 右边全是大于pivot的值
            if (left >= right) {
                break;
            }

            // 交换 左边和右边的值
            temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;

            // 如果 发现被交换的值和 pivot相等, 则将索引移位, 防止死循环
            if (arr[left] == pivot) {
                right--;
            }
            if (arr[right] == pivot) {
                left++;
            }
        }

        // 如果 左右索引相等, 则必须将 左右索引移位, 防止死循环.
        if (left == right) {
            left++;
            right--;
        }
        // 只有右索引大于左边界 才 向左递归
        if (leftBound < right) {
            quickSort3(arr, leftBound, right);
        }
        // 只有左索引小于右边界 才 向右递归
        if (left < rightBound) {
            quickSort3(arr, left, rightBound);
        }
    }


    /**
     * 网上的版本 结果正确
     */
    private static int[] quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int partitionIndex = partition(arr, left, right);
            quickSort(arr, left, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, right);
        }
        return arr;
    }

    private static int partition(int[] arr, int left, int right) {
        // 以最左边的值为基准值
        int pivot = left;
        // 以基准值右边一位为 基准线
        int index = pivot + 1;
        // 从基准线开始循环
        for (int i = index; i <= right; i++) {
            // 如果 循环值比 基准值小, 则将基准线位置的值与被循环的值 进行交换
            // 同时将基准线进行右移位, 这样 index左边的值都是比 基准值pivot小的值
            if (arr[i] < arr[pivot]) {
                swap(arr, i, index);
                index++;
            }
        }
        // 最后再将 基准值与 基准线index左边的最后一位进行交换
        // 这样 index-1 位置是 基准值pivot, 左边都是小于pivot的值, 右边都是大于pivot的值.
        swap(arr, pivot, index - 1);
        return index - 1; // 返回基准值 pivot的索引, 后续则 以该索引的左右两边为界进行排序
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
