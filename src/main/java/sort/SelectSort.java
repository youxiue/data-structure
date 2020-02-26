package sort;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 选择排序
 * 80000个数字排序时间为 6s左右, 比冒泡快很多
 */
public class SelectSort {

    @Test
    public void test() {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 80000; i++) {
            list.add((int) (Math.random() * 8000000)); //生成 [0 8000000)的数
        }
        SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.printf("开始时间:%s \n", simple.format(new Date()));
        selectSort(list);
        System.out.printf("结束时间:%s \n", simple.format(new Date()));

//        System.out.println(list.toString());

    }

    /**
     * 选择排序
     * 外层循环 i往后遍历, 内层循环获取i位置后面最小的数 与当前位置互换值,
     */
    public void selectSort(List<Integer> list) {
        int minIndex = 0;
        int min = 0;
        for (int i = 0; i < list.size(); i++) {
            minIndex = i;
            min = list.get(i);
            for (int j = i + 1; j < list.size(); j++) {
                if (min > list.get(j)) {
                    min = list.get(j);
                    minIndex = j;
                }
            }
            if(minIndex!=i){
                list.set(minIndex,list.get(i));
                list.set(i,min);
            }
        }
    }
}
