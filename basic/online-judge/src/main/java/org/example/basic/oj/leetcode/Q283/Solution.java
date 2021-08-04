package org.example.basic.oj.leetcode.Q283;

/**
 * @author anyuan
 * @since 2021-08-02 16:53
 */
public class Solution {

    public void moveZeroes(int[] nums) {
        if (nums.length < 2) {
            return;
        }
        final int size = nums.length;
        int currentPointer = 0, toBeWrittenPointer = 0;
        while (currentPointer < size) {
            if (nums[currentPointer] != 0) {
                nums[toBeWrittenPointer++] = nums[currentPointer];
            }
            currentPointer++;
        }
        while (toBeWrittenPointer < size) {
            nums[toBeWrittenPointer++] = 0;
        }
    }

}
