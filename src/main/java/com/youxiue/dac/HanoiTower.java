package com.youxiue.dac;

import org.junit.Test;

/**
 * Created by xfb on 2020/08/09 13:43.
 * 汉诺塔,  将A柱上的盘 移动到 C柱,  一次只能移动一个, 并且要保证 小盘在大盘上面
 */
public class HanoiTower {

    @Test
    public void test() {
        hanoiTower(3, 'A', 'B', 'C');
    }

    // 分治算法
    public void hanoiTower(int num, char a, char b, char c) {
        // 如果只有1个盘
        if (num == 1) {
            System.out.println("第1个盘从" + a + "-> " + c);
        } else {
            // 如果 n >=2 , 我们总是可以看做是 两个盘:  1.最下面的一个盘, 2.上面的所有盘看做是一个盘
            // 1. 将先将最上面的所有盘 A-> B, 移动过程中会用到C
            hanoiTower(num - 1, a, c, b);
            // 2. 把最下面的盘A -> C
            System.out.println("第" + num + "个盘从" + a + "-> " + c);
            // 3. 把B塔所有盘从B-> C, 移动过程中使用到A塔
            hanoiTower(num - 1, b, a, c);
        }
    }
}
