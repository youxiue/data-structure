package com.youxiue.dynamic;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created by xfb on 2020/08/09 18:04.
 * 背包问题
 */
public class KnapsackProblem {

    @Test
    public void test() {

        // 定义物品的重量
        int[] w = {1, 4, 3};
        // 定义物品的价格
        int[] val = {1500, 3000, 2000};

        // 定义背包的最大重量
        int m = 4;

        // 定义表格 , 横坐标是 重量,  纵坐标是 物品
        //
        int[][] v = new int[w.length + 1][m + 1];

        // 记录存放物品的路径
        int[][] path = new int[w.length + 1][m + 1];

        knapsackProblem(v, w, val, path);
        // 输出表格
        for (int i = 0; i < v.length; i++) {
            System.out.println(Arrays.toString(v[i]));
        }

        // 获取存放进去的物品, 倒叙查询
        int i = path.length - 1;
        int j = path[0].length - 1;
        while (i > 0 && j > 0) {
            if (path[i][j] == 1) {
                System.out.println("第" + i + "个物品放入");
                j -= w[i - 1];
            }
            i--;
        }
    }

    // 获取背包能放最大价值的方案
    public void knapsackProblem(int[][] v, int[] w, int[] val, int[][] path) {

        for (int i = 1; i < v.length; i++) {
            for (int j = 1; j < v[i].length; j++) {
                if (w[i - 1] > j) {
                    // 如果当前物品的重量大于表格重量
                    // 则将上面一个格子的值赋予这个格子
                    v[i][j] = v[i - 1][j];
                } else {
                    // 如果当前物品的重量 小于当前表格的重量
                    // 则尝试将当前物品放入格子中,  看是否价值更高
                    //v[i][j] = Math.max(v[i - 1][j], val[i - 1] + v[i - 1][j - w[i - 1]]);
                    if (val[i - 1] + v[i - 1][j - w[i - 1]] > v[i - 1][j]) {
                        v[i][j] = val[i - 1] + v[i - 1][j - w[i - 1]];
                        path[i][j] = 1;
                    } else {
                        v[i][j] = v[i - 1][j];
                    }
                }
            }
        }
    }

}
