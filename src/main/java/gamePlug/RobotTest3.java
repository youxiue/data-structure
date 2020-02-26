package gamePlug;

import java.awt.*;
import java.util.Random;

/**
 * 获取颜色
 */
public class RobotTest3 {
    public static void main(String[] args) throws AWTException {
        Robot robot = new Robot();
        Random random = new Random();

        robot.delay(5000);

        Color pixelColor = robot.getPixelColor(1900, 1000);
        System.out.println(pixelColor.getRed());
        System.out.println(pixelColor.getBlue());
        System.out.println(pixelColor.getGreen());
    }
}
