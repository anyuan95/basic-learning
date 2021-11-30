package org.example.basic.oj.leetcode.Q268;

import java.util.Arrays;

/**
 * @author anyuan
 * @since 2021-11-06 23:48
 */
class Solution {
    public int missingNumber(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] != i) {
                return i;
            }
        }
        return n;
    }
}
