package org.example.basic.oj.leetcode.Q34;

import java.util.Arrays;

/**
 * @author anyuan
 * @since 2021-09-15 09:16
 */
class Solution_0915 {

    public int[] searchRange(int[] nums, int target) {
        int n = nums.length, left = 0, right = n - 1, middle;
        int leftestIndex = -1, rightestIndex = -1;
        while (left <= right) {
            middle = left + ((right - left) >> 1);
            if (nums[middle] == target) {
                // 到右边界了，或者是下一个值比当前值（target）大，那它就是右边界
                if (middle == n - 1 || nums[middle] < nums[middle + 1]) {
                    rightestIndex = middle;
                    break;
                } else {
                    // 否则继续往右找
                    left = middle + 1;
                }
            } else if (nums[middle] < target) {
                left = middle + 1;
            } else { // middle > target
                right = middle - 1;
            }
        }

        left = 0;
        right = rightestIndex;
        while (left <= right) {
            middle = left + ((right - left) >> 1);
            // 这里只会出现<或=的情况
            if (nums[middle] < target) {
                left = middle + 1;
            } else if (nums[middle] == target) {
                // 到左边界了，或者上一个值比当前值（target）小，那它就是左边界
                if (middle == 0 || nums[middle] > nums[middle - 1]) {
                    leftestIndex = middle;
                    break;
                } else {
                    // 否则继续往左找
                    right = middle - 1;
                }
            }
        }
        return new int[]{leftestIndex, rightestIndex};
    }

    public static void main(String[] args) {
        final Solution_0915 solution = new Solution_0915();
        System.out.println(Arrays.toString(solution.searchRange(new int[]{5, 7, 7, 8, 8, 10}, 8)));
        System.out.println(Arrays.toString(solution.searchRange(new int[]{5, 7, 7, 8, 8, 10}, 9)));
        System.out.println(Arrays.toString(solution.searchRange(new int[]{5, 7, 7, 8, 8, 10}, 5)));
        System.out.println(Arrays.toString(solution.searchRange(new int[]{5, 7, 7, 8, 8, 10}, 10)));
    }
}
