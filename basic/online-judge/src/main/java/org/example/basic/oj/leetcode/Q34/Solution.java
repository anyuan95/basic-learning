package org.example.basic.oj.leetcode.Q34;

import java.util.Arrays;

/**
 * @author anyuan
 * @since 2021-08-26 16:39
 */
class Solution {

    public int[] searchRange(int[] nums, int target) {
        int first = -1, last = -1;
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int middle = left + ((right - left) >>> 1);
            if (nums[middle] == target) {
                if (middle == 0) {
                    first = 0;
                    break;
                } else if (nums[middle] > nums[middle - 1]) {
                    first = middle;
                    break;
                } else {
                    right = middle - 1;
                }
            } else if (nums[middle] < target) {
                left = middle + 1;
            } else if (nums[middle] > target) {
                right = middle - 1;
            }
        }

        if (first == -1) {
            return new int[]{-1, -1};
        }

        left = first;
        right = nums.length - 1;
        while (left <= right) {
            int middle = left + ((right - left) >>> 1);
            if (nums[middle] == target) {
                if (middle == nums.length - 1) {
                    last = nums.length - 1;
                    break;
                } else if (nums[middle] < nums[middle + 1]) {
                    last = middle;
                    break;
                } else {
                    left = middle + 1;
                }
            } else if (nums[middle] < target) {
                left = middle + 1;
            } else if (nums[middle] > target) {
                right = middle - 1;
            }
        }
        return new int[]{first, last};
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.searchRange(new int[]{5, 7, 7, 8, 8, 10}, 8)));
        System.out.println(Arrays.toString(solution.searchRange(new int[]{6, 6, 6, 6, 6, 6, 6, 6}, 6)));
        System.out.println(Arrays.toString(solution.searchRange(new int[]{6, 6, 6, 6, 6, 6, 6, 6}, 7)));
    }
}
