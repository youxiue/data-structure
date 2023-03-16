package com.youxiue.other.gamePlug;

import java.awt.*;
import java.awt.event.InputEvent;
import java.util.Random;

public class RobotTest2 {
    public static void main(String[] args) throws Exception {
        Robot robot = new Robot();
        Random random = new Random();

        robot.delay(5000);
        //移动光标位置
        robot.mouseMove(1915, 1078);
        double v1 = 0.2 + random.nextDouble() * 0.2;
        robot.delay((int) v1 * 1000);

        // 按下鼠标
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        // 设置随机延时时间（0.2 - 0.4）， 因为按下和弹起之间是有时间差的
        v1 = 0.2 + random.nextDouble() * 0.2;
        robot.delay((int) v1 * 1000);
        // 弹起k键
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }
}
