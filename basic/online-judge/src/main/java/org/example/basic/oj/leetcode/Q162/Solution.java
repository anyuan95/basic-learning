package org.example.basic.oj.leetcode.Q162;

/**
 * 寻找数组中的（任意一个）峰值
 *
 * @author anyuan
 * @since 2021-09-02 16:01
 */
class Solution {

    public int findPeakElement(int[] nums) {
        int n = nums.length, left = 0, right = n - 1, middle;
        while (left < right) {
            middle = left + ((right - left) >> 1);
            if ((middle == 0 || nums[middle] > nums[middle - 1])
                    && (middle == n - 1 || nums[middle] > nums[middle + 1])) {
                return middle;
            } else if (middle == 0 || nums[middle] < nums[middle + 1]) {
                // middle < middle+1，则最大值一定在[middle+1, right]中
                left = middle + 1;
            } else if (middle == n - 1 || nums[middle] > nums[middle + 1]) {
                // middle > middle+1，则最大值一定在[left, middle]中
                right = middle;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.findPeakElement(new int[]{1}));
        System.out.println(solution.findPeakElement(new int[]{1, 2}));
        System.out.println(solution.findPeakElement(new int[]{1, 0, 1, 0, 1}));
        System.out.println(solution.findPeakElement(new int[]{1, 2, 3, 1}));
        System.out.println(solution.findPeakElement(new int[]{1, 2, 1, 3, 5, 6, 4}));
    }
}
