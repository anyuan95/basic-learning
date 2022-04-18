package org.example.basic.oj.leetcode.Q219;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

class Solution {
    /**
     * 滑动窗口，map。没必要，因为value没有意义。
     *
     * @param nums
     * @param k
     * @return
     */
    public boolean _containsNearbyDuplicate(int[] nums, int k) {
        if (k == 0) {
            return false;
        }
        Map<Integer, Integer> map = new HashMap<>();
        final int n = nums.length;
        for (int i = 0; i < Math.min(n, k); i++) {
            if (map.containsKey(nums[i])) {
                return true;
            } else {
                map.put(nums[i], 1);
            }
        }
        for (int i = k; i < n; i++) {
            if (map.containsKey(nums[i])) {
                return true;
            } else {
                map.remove(nums[i - k]);
                map.put(nums[i], 1);
            }
        }
        return false;
    }

    /**
     * 滑动窗口，set。
     *
     * @param nums
     * @param k
     * @return
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        final HashSet<Integer> set = new HashSet<>();
        final int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (i > k) {
                set.remove(nums[i - k]);
            }
            if (set.contains(nums[i])) {
                return true;
            }
            set.add(nums[i]);
        }
        return false;
    }

}
