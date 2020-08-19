package com.youxiue.kmp;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created by xfb on 2020/08/09 20:21.
 */
public class KMPAlgorithm {


    @Test
    public void test() {
        String str1 = "BBC ABCDAB ABABABCDABDE";
        String str2 = "ABABCD";
        //String str2 = "ABCDABA";
        int[] kmpNext = kmpNext(str2);
        System.out.println(Arrays.toString(kmpNext));
        int i = kmpSearch(str1, str2, kmpNext);
        System.out.println(i);
    }


    public int kmpSearch(String str1, String str2, int[] next) {

        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();

        for (int i = 0, j = 0; i < chars1.length; i++) {

            // 如果不相等
            while (j > 0 && chars1[i] != chars2[j]) {
                j = next[j - 1];
            }

            // 如果相等则将 j ++ , 接着判断
            if (chars1[i] == chars2[j]) {
                j++;
            }

            if (j == chars2.length) {
                return i - j + 1; // 这里加 1 是因为 上面 相等的时候 j进行了 +1 , 但是i还没有来得及+ 1
            }
        }
        return 0;
    }


    // 获取部分匹配值表
    public int[] kmpNext(String str) {
        char[] chars = str.toCharArray();
        int[] next = new int[str.length()];
        // 双指针, i 指向
        for (int i = 1, j = 0; i < chars.length; i++) {
            // 如果值不相同
            while (j > 0 && chars[i] != chars[j]) {
                j = next[j - 1];
                //j = 0;
                //这里为啥不直接给 j = 0 ?????
            }

            // 如果碰到相同的值, 则将 j++ ,并赋予对应的next
            if (chars[i] == chars[j]) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }
}
