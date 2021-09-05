package org.example.basic.oj.leetcode.Q162;

/**
 * @author anyuan
 * @since 2021-09-02 16:29
 */
class Solution_better {
    public int findPeakElement(int[] nums) {
        int n = nums.length, left = 0, right = n - 1, middle;
        while (left < right) {
            middle = left + ((right - left) >> 1);
            if (nums[middle] < nums[middle+1]) {
                left = middle + 1;
            } else {
                right = middle;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        final Solution_better solution_better = new Solution_better();
        System.out.println(solution_better.findPeakElement(new int[]{1}));
        System.out.println(solution_better.findPeakElement(new int[]{1,2}));
        System.out.println(solution_better.findPeakElement(new int[]{1,2,1}));
    }
}
