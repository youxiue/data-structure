package recursion;

import org.junit.Test;

/**
 * 8皇后问题
 * 8X8 的棋盘上 放置8 个皇后, 每个皇后 不能在同行同列 以及不能在斜线上
 */
public class Queue8 {
    int max = 8;
    /**
     * 这里使用int 数组来代替二维数组,
     * 坐标 表示第几行, 值表示第几列
     */
    int[] array = new int[max];
    int count = 0;


    @Test
    public void main(){
        check(0);
        System.out.printf("一共%d种解法",count);
    }

    public void check(int n){
        if( n == max ){
            // 如果n == 8 表明 已经找到一种解法
            print();
            count ++;
            return;
        }else {

            for (int i = 0; i < max; i++) {
                array[n] = i;
                if(judge(n)){
                    // 如果当前位置不冲突 , 则 摆放下一行
                    check(n+1);
                }
            }
        }
    }

    // 将皇后摆放的位置 打印出来
    public void print(){
        for (int i : array) {
            System.out.print(i+" ");
        }
        System.out.println();
    }

    // 校验当前摆放位置 是否 冲突
    public boolean judge(int n){
        for (int i = 0; i < n; i++) {
            // 校验当前摆放位置 是否与 之前的位置 在同一列 或者在斜线上
            if(array[i] == array[n] || Math.abs(n-i) == Math.abs(array[n]-array[i])){
                return false;
            }
        }
        return true;
    }

}
