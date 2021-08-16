package org.example.basic.oj.leetcode.Q283;

/**
 * @author anyuan
 * @since 2021-08-02 16:53
 */
public class Solution {
    public void moveZeroes(int[] nums) {
        int moveIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[moveIndex++] = nums[i];
            }
        }
        while (moveIndex < nums.length) {
            nums[moveIndex++] = 0;
        }
    }

}
