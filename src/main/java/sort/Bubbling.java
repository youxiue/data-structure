package sort;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 冒泡排序
 * 80000个数据排序时间为 28秒左右
 */
public class Bubbling {
    @Test
    public void test() {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 80000; i++) {
            list.add((int) (Math.random() * 8000000)); //生成 [0 8000000)的数
        }
        SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.printf("开始时间:%s \n", simple.format(new Date()));
        bubblingSort(list);
        System.out.printf("结束时间:%s \n", simple.format(new Date()));
        //

//        System.out.println(list.toString());

    }

    /**
     * 冒泡排序
     * 时间复杂度 O(n²)
     *
     * @param list
     */
    public void bubblingSort(List<Integer> list) {
        int temp = 0;
        boolean flag = false;
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.size() - i - 1; j++) {
                if (list.get(j) > list.get(j + 1)) {
                    // 如果当前位置比后一位数字大, 则交换两者的位置
                    temp = list.get(j + 1);
                    list.set(j + 1, list.get(j));
                    list.set(j, temp);
                    flag = true;
                }
            }
           /* System.out.printf("第%d趟排序:",i);
            System.out.println(list.toString());*/
            // 优化 如果没有发生交换 ,说明顺序已经是 从小到大的顺序了
            if (!flag) {
                // 说明一次交换都没有  则 结束循环
                break;
            } else {
                flag = false; // 复位, 进行下一次的判断
            }
        }
    }




    public void bubblingSort2(List<Integer> list) {
        int temp = 0;
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(i) > list.get(j)) {
                    temp = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, temp);
                }
            }
        }
    }

}