package org.example.basic.oj.leetcode.Q398;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author anyuan
 * @date 2022-04-25 22:55
 */
class Solution {

    private Map<Integer, List<Integer>> map;

    public Solution(int[] nums) {
        map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            map.computeIfAbsent(num, k -> new ArrayList<>()).add(i);
        }
    }

    public int pick(int target) {
        final List<Integer> list = map.get(target);
        final int i = new Random().nextInt(list.size());
        return list.get(i);
    }
}
