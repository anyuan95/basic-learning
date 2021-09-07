package org.example.basic.oj.leetcode.Q704;

/**
 * @author anyuan
 * @since 2021-09-06 15:33
 */
class Solution_0906 {
    public int search(int[] nums, int target) {
        int n = nums.length, left = 0, right = n - 1, mid;
        while (left <= right) {
            mid = left + ((right - left) >> 1);
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
}
