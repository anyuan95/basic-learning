package org.example.basic.oj.leetcode.Q961;

class Solution {
    public int repeatedNTimes(int[] nums) {
        if (nums[0] == nums[2]) {
            return nums[0];
        }
        int n = nums.length / 2;
        for (int i = 0; i < n; i++) {
            if (nums[i] == nums[i + 1]) {
                return nums[i];
            }
        }
        return nums[0];
    }

    public int _repeatedNTimes(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int t = nums[i];
            if (i - 1 >= 0 && nums[i - 1] == t) return t;
            if (i - 2 >= 0 && nums[i - 2] == t) return t;
            if (i - 3 >= 0 && nums[i - 3] == t) return t;
        }
        return -1;
    }

}
