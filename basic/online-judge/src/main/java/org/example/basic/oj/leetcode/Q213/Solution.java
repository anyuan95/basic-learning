package org.example.basic.oj.leetcode.Q213;

/**
 * @author anyuan
 * @since 2021-09-14 22:09
 */
class Solution {
    /**
     * 提供的数组是一个环形结构，即n-1的下一个位置是0
     * 要求取最大价值和，取东西时不能取相邻的
     * <p>
     * dp[i] 表示到第i位置结束的话，能获取的最大收益
     * <p>
     * 1 <= nums.length <= 100
     * 0 <= nums[i] <= 1000
     *
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        final int n = nums.length;
        if (n == 1) {
            return nums[0];
        } else if (n == 2) {
            return Math.max(nums[0], nums[1]);
        }
        return Math.max(process(nums, 0, n - 2), process(nums, 1, n - 1));
    }

    private int process(int[] nums, int start, int end) {
        int first = nums[start], second = Math.max(nums[start], nums[start + 1]);
        for (int i = 2 + start; i <= end; i++) {
            int temp = Math.max(first + nums[i], second);
            first = second;
            second = temp;
        }
        return second;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.rob(new int[]{1, 2, 3}));
        System.out.println(solution.rob(new int[]{1, 2, 3, 1}));
        System.out.println(solution.rob(new int[]{200, 3, 140, 20, 10}));
    }

}
