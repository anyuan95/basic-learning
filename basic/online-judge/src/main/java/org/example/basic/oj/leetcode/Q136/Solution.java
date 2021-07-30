package org.example.basic.oj.leetcode.Q136;

/**
 * @author anyuan
 * @since 2021-07-30 09:35
 */
class Solution {
    public int singleNumber(int[] nums) {
        int target = 0;
        for (int num : nums) {
            target ^= num;
        }
        return target;
    }
}
