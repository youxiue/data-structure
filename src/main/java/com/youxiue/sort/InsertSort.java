package com.youxiue.sort;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 插入排序
 * 80000个数字排序时间为 3s左右
 */
public class InsertSort {

    @Test
    public void test() {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 80000; i++) {
            list.add((int) (Math.random() * 8000000)); //生成 [0 8000000)的数
        }
        SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.printf("开始时间:%s \n", simple.format(new Date()));
        insertSort(list);
        System.out.printf("结束时间:%s \n", simple.format(new Date()));
//        System.out.println(list);
    }


    /**
     * 排序思想:
     * 分为两个数组, 前面为有序数组, 后面为无序数组
     * 后面的无序数组每次取第一个值,与有序数组进行比较, 然后插入到合适的位置
     *
     * @param list
     */
    public void insertSort(List<Integer> list) {
        int index = 0;
        int value = 0;
        for (int i = 1; i < list.size(); i++) {
            index = i - 1; // 前面有序数组的 最后一个的下标
            value = list.get(i); // 后面无序数组的第一位
            // 将当前值 与 之前的值 进行比较 如果比当前值大 则向前移位, 继续比较
            while (index >= 0 && value < list.get(index)) {
                // 将index 位置值向后移动一位 ,将前面空出来一个位置, 便于插入
                list.set(index + 1, list.get(index));
                index--;
            }
            // index+1 是因为 index 位置数字比 当前i 位置数字(value)小, 所以 需要插到index 后面一位
            if (index + 1 != i) {
                list.set(index + 1, value);
            }
        }
    }
}
