package com.youxiue.sort;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by xfb on 2020/07/26 21:51.
 * 堆排序
 * 800000 105ms
 * <p>
 * 大顶堆: 每个节点的值都大于或等于其左右子节点的值, 称为大顶堆,  左右子节点之间无所谓大小关系.
 * 小顶堆: 每个节点的值都小于或等于其左右子节点的值.
 *
 * 适用场景: 选出前几名, 不是全部排序的时候
 */
public class HeapSort {

    @Test
    public void test() {
        //int[] arr = {11,10,9,8,7,6,4,3,5,2,1};
        //heapSort(arr);

        int[] arr = new int[800000];
        for (int i = 0; i < 800000; i++) {
            //生成 [0 8000000)的数+
            arr[i] = (int) (Math.random() * 8000000);
        }
        SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startDate = new Date();
        heapSort(arr);
        Date endDate = new Date();
        System.out.printf("开始时间:%s \n结束时间:%s \n共耗时:%s", simple.format(startDate), simple.format(endDate), endDate.getTime() - startDate.getTime());
        //System.out.println(Arrays.toString(arr));
    }

    public void heapSort(int[] arr) {

        // 从最后一个非叶子节点开始 , 将数组排成 大顶堆
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adJustHeap(arr, i, arr.length);
        }

        //
        for (int i = arr.length - 1; i > 0; i--) {
            int temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;
            adJustHeap(arr, 0, i);
        }
        //System.out.println(Arrays.toString(arr));
    }


    /**
     * @param arr    被排序的数组
     * @param i
     * @param length 要排序的长度
     */
    public void adJustHeap(int[] arr, int i, int length) {
        int temp = arr[i];

        // 将k  放到i 的左子节点位置, 然后 循环向子节点比较, 直到找到子节点中最大的,
        // 并且每一次 循环都将 父和两子中最大的数 提到了 父的位置
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            // 比较左右子节点哪个大, 如果右子节点比较大, 则将k 移到右子节点位置
            if (k + 1 < length && arr[k] < arr[k + 1]) {
                k++;
            }
            if (arr[k] > temp) { // 如果子节点的值大于 temp 的值, 则将子节点的值 赋予父节点, i也是在不断移动的
                arr[i] = arr[k];
                i = k;
            } else {
                break;
            }
        }
        arr[i] = temp;
    }
}
