package org.example.basic.oj.leetcode.Q1;

import java.util.HashMap;

/**
 * @author anyuan
 * @since 2021-08-05 15:03
 */
class Solution {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                return new int[]{i, map.get(nums[i])};
            } else {
                map.put(target-nums[i], i);
            }
        }
        return null;
    }
}
