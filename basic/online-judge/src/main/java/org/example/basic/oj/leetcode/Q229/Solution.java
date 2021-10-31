package org.example.basic.oj.leetcode.Q229;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author anyuan
 * @date 2021-10-22 10:23
 */
class Solution {
    /**
     * 题目要求：统计出现次数超过n/3的元素
     * 最简单的方法就是使用map记录每个值出现的次数，最后遍历次数map进行统计
     *
     * @param nums
     * @return
     */
    public List<Integer> majorityElement(int[] nums) {
        HashMap<Integer, Integer> numberCountMap = new HashMap<>();
        for (int num : nums) {
            numberCountMap.compute(num, (number, count) -> count == null ? 1 : count + 1);
        }
        int times = nums.length / 3;
        List<Integer> answer = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : numberCountMap.entrySet()) {
            if (entry.getValue() > times) {
                answer.add(entry.getKey());
            }
        }
        return answer;
    }
}
