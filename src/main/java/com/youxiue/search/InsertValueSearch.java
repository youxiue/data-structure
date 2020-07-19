package com.youxiue.search;

import org.junit.Test;

/**
 * 插值查找算法
 * 公式变为: left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left])
 * 对于数据量较大, 关键字分布比较均匀的查找表来说, 采用差值查找,速度较快
 * 关键字分布不均匀的情况下, 该方法不一定比折半查找好
 * Created by xfb on 2020/07/14 22:40.
 */
public class InsertValueSearch {

    @Test
    public void test() {
        /*int[] arr = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = i + 1;
        }*/
        int[] arr = {1, 8, 10, 89, 1000, 1189, 1234};
        int result = insertValueSearch(arr, 0, arr.length - 1, 10);
        System.out.println(result);
    }

    public int insertValueSearch(int[] arr, int left, int right, int findVal) {
        // findVal < arr[0] || findVal > arr[arr.length - 1]  必须要, 不仅能优化, 还能防止越界
        if (left > right || findVal < arr[0] || findVal > arr[arr.length - 1]) {
            return -1;
        }

        int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);
        System.out.println(mid);
        int midValue = arr[mid];
        if (findVal > midValue) {
            return insertValueSearch(arr, mid + 1, right, findVal);
        } else if (findVal < midValue) {
            return insertValueSearch(arr, left, mid - 1, findVal);
        } else {
            return mid;
        }
    }
}
