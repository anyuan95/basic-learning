package org.example.basic.oj.leetcode.Q167;

/**
 * @author anyuan
 * @since 2021-08-05 15:11
 */
class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int leftPointer = 0, rightPointer = numbers.length - 1;
        while (leftPointer < rightPointer) {
            final int sum = numbers[leftPointer] + numbers[rightPointer];
            if (sum == target) {
                return new int[]{leftPointer + 1, rightPointer + 1};
            } else if (sum > target) {
                rightPointer--;
            } else {
                leftPointer++;
            }
        }
        return null;
    }
}
