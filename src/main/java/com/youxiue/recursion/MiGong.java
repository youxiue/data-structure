package com.youxiue.recursion;

import org.junit.Test;

public class MiGong {
    /**
     * 迷宫寻路
     */
    @Test
    public void findPath() {
        // 先定义地图
        int[][] map = new int[7][8];
        // 设置横向墙
        for (int i = 0; i < 8; i++) {
            map[0][i] = 1;
            map[6][i] = 1;
        }
        // 设置纵向墙
        for (int i = 0; i < 7; i++) {
            map[i][0] = 1;
            map[i][7] = 1;
        }
        // 中间设置挡板
        map[3][1] = 1;
        map[3][2] = 1;
        map[4][4] = 1;
        map[5][4] = 1;

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

        setWay(map, 1, 1);
        System.out.println("寻路的路径地图:");
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * 找路的方法
     * 规则: 0 表示还没有走 1 表示墙 2 表示通路  3 表示不通
     * 寻路规则: 下 -> 右 -> 上 -> 左
     *
     * @return
     */
    public boolean setWay(int[][] map, int i, int j) {


        if (map[5][6] == 2) {
            // 如果已经到达终点 则返回
            return true;
        } else {

            if (map[i][j] == 0) {
                // 将当前 位置 置为2
                map[i][j] = 2;
                // 下一步
                if (setWay(map, i + 1, j)) {  // 向下
                    return true;
                } else if (setWay(map, i, j + 1)) { // 向右
                    return true;
                } else if (setWay(map, i - 1, j)) { // 向上
                    return true;
                } else if (setWay(map, i, j - 1)) { // 向左
                    return true;
                } else {// 如果上下左右都不通  则 将该点置为3 并返回false
                    map[i][j] = 3;
                    return false;
                }
            } else {
                // 如果不是0  说明是 1 2 3
                return false;
            }
        }
    }
}
