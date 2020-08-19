package com.youxiue.horse;

import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

/**
 * Created by xfb on 2020/08/18 21:58.
 */
public class HorseChessboard {

    public int X; // 棋盘行数

    public int Y; // 棋盘列数
    public boolean isOver;

    @Test
    public void test() {

        X = 8;
        Y = 8;
        // 定义一个棋盘
        int[][] chessboard = new int[X][Y];

        Date startDate = new Date();
        traversalChessboard(chessboard, 5, 2, 1);
        Date endDate = new Date();
        System.out.println(endDate.getTime() - startDate.getTime());
        // 排序优化前: 208106  排序优化后:3ms   差别这么大的吗?

        for (int[] rows : chessboard) {
            System.out.println(Arrays.toString(rows));
        }
    }


    /**
     * @param chessboard 棋盘
     * @param row        当前棋子的行数
     * @param column     当前棋子的列数
     * @param step       当前的步数
     */
    public void traversalChessboard(int[][] chessboard, int row, int column, int step) {
        // 棋盘 在当前棋子的位置 记录当前的步数
        chessboard[row][column] = step;

        // 获取下面可以走的位置集合
        ArrayList<Point> list = next(new Point(row, column));

        sort(list); // 按照下一步可以走的数量多少  进行排序,  贪心算法, 先走 下一步可选多的

        while (!list.isEmpty()) {
            // 获取可以往下走的第一个 位置
            Point point = list.remove(0);
            // 判断这个位置 是否被访问过, 并且
            if (chessboard[point.x][point.y] == 0) {
                traversalChessboard(chessboard, point.x, point.y, step + 1);
            }
        }
        // 如果步数 小于 棋盘的格子数 , 说明没有走完, 说明这条路不对,
        if (step < X * Y && !isOver) {
            // 则将已经置为step的当前位置 重新置为0
            chessboard[row][column] = 0;
        } else {
            // 如果等于了,  说明走完了,   则退出循环
            isOver = true;
        }


    }


    // 获取 可以走一下步 的集合
    public ArrayList<Point> next(Point curPoint) {
        ArrayList<Point> list = new ArrayList<>();
        int tempX = curPoint.x;
        int tempY = curPoint.y;
        if (tempX - 2 >= 0 && tempY - 1 >= 0) {
            list.add(new Point(tempX - 2, tempY - 1));
        }
        if (tempX - 1 >= 0 && tempY - 2 >= 0) {
            list.add(new Point(tempX - 1, tempY - 2));
        }
        if (tempX + 2 < X && tempY - 1 >= 0) {
            list.add(new Point(tempX + 2, tempY - 1));
        }
        if (tempX + 1 < X && tempY - 2 >= 0) {
            list.add(new Point(tempX + 1, tempY - 2));
        }
        if (tempX + 2 < X && tempY + 1 < Y) {
            list.add(new Point(tempX + 2, tempY + 1));
        }
        if (tempX + 1 < X && tempY + 2 < Y) {
            list.add(new Point(tempX + 1, tempY + 2));
        }
        if (tempX - 2 >= 0 && tempY + 1 < Y) {
            list.add(new Point(tempX - 2, tempY + 1));
        }
        if (tempX - 1 >= 0 && tempY + 2 < Y) {
            list.add(new Point(tempX - 1, tempY + 2));
        }
        return list;
    }

    public void sort(ArrayList<Point> ps){
        ps.sort(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                ArrayList<Point> list1 = next(o1);
                ArrayList<Point> list2 = next(o2);
                int s1 = list1.size();
                int s2 = list2.size();
                return Integer.compare(s1, s2);
            }
        });
    }

}
