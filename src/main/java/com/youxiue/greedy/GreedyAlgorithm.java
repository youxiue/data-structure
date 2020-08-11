package com.youxiue.greedy;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Created by xfb on 2020/08/11 22:28.
 * 贪婪算法
 */
public class GreedyAlgorithm {


    /**
     * 5个电台 k1 ~ k5,  每个电台覆盖的地区不一样 , 找出一种组合 覆盖所有地区
     */
    @Test
    public void test() {

        HashMap<String, HashSet<String>> map = new HashMap<>();

        //  k1 ~ k5 每个电台覆盖的地区
        HashSet<String> set1 = new HashSet<>();
        set1.add("北京");
        set1.add("上海");
        set1.add("天津");
        HashSet<String> set2 = new HashSet<>();
        set2.add("广州");
        set2.add("北京");
        set2.add("深圳");
        HashSet<String> set3 = new HashSet<>();
        set3.add("成都");
        set3.add("上海");
        set3.add("杭州");
        HashSet<String> set4 = new HashSet<>();
        set4.add("上海");
        set4.add("天津");
        HashSet<String> set5 = new HashSet<>();
        set5.add("杭州");
        set5.add("大连");

        map.put("k1", set1);
        map.put("k2", set2);
        map.put("k3", set3);
        map.put("k4", set4);
        map.put("k5", set5);

        // 所有的地区
        HashSet<String> allAreas = new HashSet<>();
        allAreas.add("北京");
        allAreas.add("上海");
        allAreas.add("天津");
        allAreas.add("广州");
        allAreas.add("深圳");
        allAreas.add("成都");
        allAreas.add("杭州");
        allAreas.add("大连");


        // 定义一个 集合 用于 存放 选择的电台
        ArrayList<String> selects = new ArrayList<>();

        // 临时set 用于存放 电台与所有地区的交集
        HashSet<String> tempSet = new HashSet<>();
        String maxKey = null;

        // 没选择一个电台 ,将该电台覆盖的区域从 allAreas中去除, 便于下次比较
        while (allAreas.size() > 0) {
            maxKey = null;
            int maxSize = 0;
            for (String key : map.keySet()) {
                tempSet.clear();
                // 当前电台的所有覆盖地区
                HashSet<String> set = map.get(key);
                tempSet.addAll(set);
                // 当前电台和 所有地区的重复 地区
                tempSet.retainAll(allAreas);

                // 如果tempSet和 所有地区有重复覆盖的 找到覆盖地区最多的
                /*if (tempSet.size() > 0 && (maxKey == null || tempSet.size() > map.get(maxKey).size())) {
                    maxKey = key;
                }*/
                // 上面韩老师的 tempSet.size() > map.get(maxKey).size() 这个版本感觉不对
                // 这里 tempSet.size() 是交集的大小,  map.get(maxKey).size() 并不是交集的大小, 不应该这么比
                if (tempSet.size() > maxSize) {
                    maxKey = key;
                    maxSize = tempSet.size();
                }
            }
            // 如果找到了
            if(maxKey != null){
                // 将选中的电台加入到 selects 中
                selects.add(maxKey);
                // 将选中电台的覆盖地区从 所有地区中移除, 方便 下次选出 覆盖剩余地区最多的
                allAreas.removeAll(map.get(maxKey));
            }
        }

        System.out.println(selects);
    }
}
