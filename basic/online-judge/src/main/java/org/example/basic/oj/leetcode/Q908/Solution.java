package org.example.basic.oj.leetcode.Q908;

class Solution {
    public int smallestRangeI(int[] nums, int k) {
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int num : nums) {
            if (num < min) {
                min = num;
            }
            if (num > max) {
                max = num;
            }
        }
        return Math.max(max - min - k * 2, 0);
    }
}
