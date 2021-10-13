package org.example.basic.oj.leetcode.Q45;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author anyuan
 * @date 2021-10-13 20:37
 */
class Solution_1013 {
    /**
     * 前提：题目给定的数组一定是可以从0位置到n-1位置的
     * dp，双层循环，巨慢
     *
     * @param nums
     * @return
     */
    public int jump(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }
        int n = nums.length;
        // 记录i位置最少需要多少步能走到
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 0; i < n; i++) {
            final int currentStepFurthestIndex = i + nums[i];
            if (currentStepFurthestIndex >= n - 1) {
                return dp[i] + 1;
            }
            // 从i位置出发能够到达的所有位置，尝试去更新这些位置的最少步数
            for (int j = i + 1; j <= currentStepFurthestIndex; j++) {
                dp[j] = Math.min(dp[j], dp[i] + 1);
            }
        }
        return dp[n - 1];
    }

    public int jumpx(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }
        final int n = nums.length;
        int maxCanReachIndex = 0, lastStepMaxCanReachIndex = 0, step = 0;
        for (int i = 0; i < n; i++) {
            maxCanReachIndex = Math.max(maxCanReachIndex, i + nums[i]);
            if (maxCanReachIndex >= n - 1) {
                return step + 1;
            }
            if (i == lastStepMaxCanReachIndex) {
                step++;
                lastStepMaxCanReachIndex = maxCanReachIndex;
            }
        }
        return step;
    }


    public static void main(String[] args) {
        final Solution_1013 solution = new Solution_1013();
        System.out.println(solution.jumpx(new int[]{2, 3, 1, 1, 4}));
        System.out.println(solution.jumpx(new int[]{2, 3, 0, 1, 4}));
        System.out.println(solution.jumpx(new int[]{1, 2, 3}));
    }
}
