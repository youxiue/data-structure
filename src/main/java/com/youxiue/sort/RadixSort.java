package com.youxiue.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 基数排序
 * Created by xfb on 2020/07/12 17:01.
 * 800000 用时 55ms
 * 8000000 用时 460ms左右
 */
public class RadixSort {

    public static void main(String[] args) {
        /*int[] arr = {53, 3, 542, 748, 14, 214};
        radix(arr);*/
        int[] arr = new int[800000];
        for (int i = 0; i < 800000; i++) {
            //生成 [0 8000000)的数+
            arr[i] = (int) (Math.random() * 8000000);
        }
        SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startDate = new Date();
        radix(arr);
        Date endDate = new Date();
        System.out.printf("开始时间:%s \n结束时间:%s \n共耗时:%s", simple.format(startDate), simple.format(endDate), endDate.getTime() - startDate.getTime());
        //System.out.println(Arrays.toString(arr));
    }


    public static void radix(int[] arr) {

        // 获取 数组中最长的位数
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        int length = (max + "").length();
        // 定义十个捅, 每个桶的深度都是 arr.length
        int[][] bucket = new int[10][arr.length];
        // 再定义一个长度为10的数组, 对应每个桶中值的数量
        int[] bucketLength = new int[10];

        int n = 1; // 每次除以的数  便于获取对应的位值

        for (int i = 0; i < length; i++) { // 循环长度
            for (int j = 0; j < arr.length; j++) {
                // 获取 数据 每位的值, 放入对应的桶中
                int temp = arr[j] / n % 10;
                bucket[temp][bucketLength[temp]] = arr[j];
                bucketLength[temp]++; // 对应桶的深度+1
            }
            //System.out.println(Arrays.toString(arr));
            n *= 10;
            // 将桶中的数据拿出来. 重新放入到arr中
            int y = 0;
            for (int k = 0; k < bucket.length; k++) {
                if(bucketLength[k] != 0){
                    for (int x = 0; x < bucketLength[k]; x++) {
                        arr[y] = bucket[k][x];
                        y++;
                    }
                }
                bucketLength[k] = 0;
            }

            //System.out.println(Arrays.toString(arr));
        }
        //System.out.println(Arrays.toString(arr));
    }
}
