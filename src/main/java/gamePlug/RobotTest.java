package gamePlug;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;

/**
 * 机器人测试
 * 测试按键按下弹起
 */
public class RobotTest {
    public static void main(String[] args) throws Exception {
        // 创建一个java 机器人对象
        Robot robot = new Robot();

        // 延时
        robot.delay(5000);
        Random random = new Random();

        while(true){
            // 按下k键
            robot.keyPress(KeyEvent.VK_K);
            // 设置随机延时时间（0.2 - 0.4）， 因为按下和弹起之间是有时间差的
            double v1 = 0.2 + random.nextDouble()*0.2;
            robot.delay((int)v1*1000);
            // 弹起k键
            robot.keyRelease(KeyEvent.VK_K);
            //设置随机延时时间（2 - 4）， 每次按键之间是有时间差的
            double v2 = 2 + random.nextDouble()*2;
            robot.delay((int)v2*1000);
        }
    }



}
