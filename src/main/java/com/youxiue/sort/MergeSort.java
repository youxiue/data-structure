package com.youxiue.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by xfb on 2020/07/05 17:28.
 * 归并排序
 */
public class MergeSort {

    public static void main(String[] args) {
        int[] arr = {9, 8, 8, 1, 0, 2, 3, 5, 4, 6, 7, 4};
        /*int[] arr = new int[800000];
        for (int i = 0; i < 800000; i++) {
            //生成 [0 8000000)的数+
            arr[i] = (int) (Math.random() * 8000000);
        }*/
        SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startDate = new Date();
        //mergeSort(arr, 0, arr.length - 1);
        merge_sort(arr);
        Date endDate = new Date();
        System.out.printf("开始时间:%s \n结束时间:%s \n共耗时:%s", simple.format(startDate), simple.format(endDate), endDate.getTime() - startDate.getTime());
        System.out.println(Arrays.toString(arr));
    }

    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int[] temp = new int[right - left + 1];
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right, temp);
        }
    }


    /**
     * @param arr   要排序的数组
     * @param left  被分组的最左边索引
     * @param mid   中间索引
     * @param right 被分组的最右边索引
     * @param temp  临时数组
     */
    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int l = left;
        int r = mid + 1;
        int t = 0;

        while (l <= mid && r <= right) {
            // 如果 中间索引 mid 左边的值小于右边的值, 则将左边的值填充到temp中,
            // 否则将右边的值 填充到temp中
            // 直到左边索引到达mid 或 右边的索引到达right ,然后退出循环
            if (arr[l] < arr[r]) {
                temp[t] = arr[l];
                l++;
            } else {
                temp[t] = arr[r];
                r++;
            }
            t++;
        }
        // 因为已经有一边已经全部移到 temp[] 中,  所以将另一边全部移到temp[] 中.
        while (l <= mid) {
            temp[t] = arr[l];
            l++;
            t++;
        }
        while (r <= right) {
            temp[t] = arr[r];
            r++;
            t++;
        }
        // 最后将 temp 中的值 复制到 arr中
        t = 0;
        int tempLeft = left;
        while (tempLeft <= right) {
            arr[tempLeft] = temp[t];
            tempLeft++;
            t++;
        }
        //System.out.println("arr:"+Arrays.toString(arr));
        //System.out.println("temp:"+Arrays.toString(temp));
    }


    //

    /**
     * 归并排序（Java-迭代版）
     * ① 申请空间，使其大小为两个已经排序序列之和，该空间用来存放合并后的序列
     * ② 设定两个指针，最初位置分别为两个已经排序序列的起始位置
     * ③ 比较两个指针所指向的元素，选择相对小的元素放入到合并空间，并移动指针到下一位置
     * ④ 重复步骤③直到某一指针到达序列尾
     * ⑤ 将另一序列剩下的所有元素直接复制到合并序列尾
     */
    public static void merge_sort(int[] arr) {
        int len = arr.length;
        int[] result = new int[len];
        int block, start;

        // 原版代码的迭代次数少了一次，没有考虑到奇数列数组的情况
        for (block = 1; block < len * 2; block *= 2) { // 这个是划定每组的数量  , 每次都是两倍的增长
            for (start = 0; start < len; start += 2 * block) { // 起始位置.  每次都是 从0 开始, 两倍于每组的数量 往右移
                System.out.println("block: "+block+" | start: "+start);
                int low = start;
                int mid = (start + block) < len ? (start + block) : len;
                int high = (start + 2 * block) < len ? (start + 2 * block) : len;
                //两个块的起始下标及结束下标
                int start1 = low, end1 = mid;
                int start2 = mid, end2 = high;
                //开始对两个block进行归并排序
                while (start1 < end1 && start2 < end2) {
                    result[low++] = arr[start1] < arr[start2] ? arr[start1++] : arr[start2++];
                }
                while (start1 < end1) {
                    result[low++] = arr[start1++];
                }
                while (start2 < end2) {
                    result[low++] = arr[start2++];
                }
            }
            // 将 result 与 arr 进行交换  得到排序后的 数组
            int[] temp = arr;
            arr = result;
            result = temp;
        }
        result = arr;
    }
}
