package com.youxiue.repeat;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created by xfb on 2020/07/12 15:52.
 */
public class RepeatNum {

    @Test
    public void test(){
        int[] nums = {4, 6, 1, 0, 2, 5, 0};
        int repeatNumber = findRepeatNumber(nums);
        System.out.println("result:"+repeatNumber);
    }

    public int findRepeatNumber(int[] nums){
        int temp;
        for(int i=0;i<nums.length;i++){
            System.out.println(Arrays.toString(nums));
            while (nums[i]!=i){
                System.out.println("---"+Arrays.toString(nums));
                if(nums[i]==nums[nums[i]]){
                    return nums[i];
                }
                temp=nums[i];
                nums[i]=nums[temp];
                nums[temp]=temp;
            }
        }
        return -1;
    }


}
