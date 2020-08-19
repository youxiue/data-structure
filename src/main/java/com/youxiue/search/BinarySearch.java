package com.youxiue.search;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 二分查找法
 * 公式为: (left + right) / 2
 * Created by xfb on 2020/07/13 22:47.
 */
public class BinarySearch {

    @Test
    public void test() {
        int[] arr = {1, 8, 10, 89, 1000, 1000, 1000, 1234};
        //int i = binarySearch(arr, 0, arr.length - 1, 80);
        //System.out.println(i);
        //ArrayList<Integer> integers = binarySearch2(arr, 0, arr.length - 1, 1000);

        /*int erfen = erfen(arr, 0, arr.length - 1);
        System.out.println(erfen);*/


        //System.out.println(integers.toString());
        int i = binarySearchNoRecur(arr, 1234);
        System.out.println(i);
    }

    /**
     * Created by xfb on  2020/7/13 22:55.
     *
     * @Description 返回找到的下标
     **/
    public int binarySearch(int[] arr, int left, int right, int findVal) {

        if (left > right) {
            return -1;
        }

        int mid = (left + right) / 2;

        int midValue = arr[mid];

        if (midValue < findVal) {
            return binarySearch(arr, mid + 1, right, findVal);
        } else if (midValue > findVal) {
            return binarySearch(arr, left, mid - 1, findVal);
        } else {
            return mid;
        }

    }

    /**
     * 返回所有找到的 下标
     *
     * @return
     */
    public ArrayList<Integer> binarySearch2(int[] arr, int left, int right, int findVal) {

        if (left > right) {
            return new ArrayList<>();
        }

        int mid = (left + right) / 2;

        int midValue = arr[mid];

        if (midValue < findVal) {
            return binarySearch2(arr, mid + 1, right, findVal);
        } else if (midValue > findVal) {
            return binarySearch2(arr, left, mid - 1, findVal);
        } else {
            ArrayList<Integer> list = new ArrayList<>();

            int temp = mid - 1;
            // 往mid 左边查询 看是否有相同的数据
            while (temp > 0 && arr[temp] == midValue) {
                list.add(temp);
                temp--;
            }
            list.add(mid);
            temp = mid + 1;
            // 往mid 左边查询 看是否有相同的数据
            while (temp < arr.length - 1 && arr[temp] == midValue) {
                list.add(temp);
                temp++;
            }
            return list;
        }
    }



    public int erfen(int[]nums , int left, int right) {
        if (left > right) {
            return -1;
        }
        // 获取中间索引
        int mid = (left + right) / 2;
        int leftMagic = erfen(nums, left, mid - 1);
        if (leftMagic != -1) {
            return leftMagic;
        } else if (nums[mid] == mid) {
            return mid;
        }
        return erfen(nums, mid + 1, right);

    }
    /**
     * 非递归的二分查找
     *
     * @return
     */
    public int binarySearchNoRecur(int[] nums, int findVal) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == findVal) {
                return mid;
            } else if (findVal < nums[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }
}
