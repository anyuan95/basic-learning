package org.example.basic.oj.leetcode.Q162;

/**
 * @author anyuan
 * @since 2021-09-18 15:54
 */
class Solution_better {
    public int findPeakElement(int[] nums) {
        int n = nums.length, left = 0, right = n - 1, middle;
        while (left < right) {
            middle = left + ((right - left) >> 1);
            if (nums[middle] < nums[middle +1]) {
                // 如果 mid < mid + 1，则mid一定不是最大值，最大值在(mid,right]中
                left = middle + 1;
            } else {
                // 如果 mid > mid + 1，则mid有可能是最大值，最大值在[left,mid]中
                right = middle;
            }
        }
        return left;
    }
}
