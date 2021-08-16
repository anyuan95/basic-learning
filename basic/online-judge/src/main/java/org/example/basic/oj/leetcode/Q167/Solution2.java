package org.example.basic.oj.leetcode.Q167;

import java.util.Arrays;

/**
 * @author anyuan
 * @since 2021-08-15 12:33
 */
class Solution2 {

    /**
     * 在原来的算法上添加了二分查找。
     * 加快第二个数的查找速度。
     *
     * @param numbers
     * @param target
     * @return
     */
    public int[] twoSum(int[] numbers, int target) {
        for (int i = 0; i < numbers.length - 1; i++) {
            // start = i+1，避免当前值又算进去
            final int targetIndex = binarySearch(numbers, i + 1, numbers.length - 1, target - numbers[i]);
            if (targetIndex != -1) {
                return new int[]{i + 1, targetIndex + 1};
            }
        }
        return null;
    }

    private int binarySearch(int[] nums, int start, int end, int target) {
        int middle;
        while (start <= end) {
            middle = start + ((end - start) >> 1);
            if (nums[middle] == target) {
                return middle;
            } else if (nums[middle] > target) {
                end = middle - 1;
            } else {
                start = middle + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution2().twoSum(new int[]{1, 2, 3, 4, 4, 9, 56, 90}, 8)));
    }
}
