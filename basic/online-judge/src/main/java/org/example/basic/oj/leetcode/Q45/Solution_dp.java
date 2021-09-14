package org.example.basic.oj.leetcode.Q45;

import java.util.Arrays;

/**
 * @author anyuan
 * @since 2021-09-14 23:17
 */
class Solution_dp {
    /**
     * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
     * 假设你总是可以到达数组的最后一个位置。
     * <p>
     * 1 <= nums.length <= 104
     * 0 <= nums[i] <= 1000
     *
     * @param nums
     * @return
     */
    public int jump(int[] nums) {
        final int n = nums.length;
        if (n == 1) {
            return 0;
        }
        // 记录走到i位置需要的最少步数
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 0; i < n; i++) {
            // 如果当前节点只能走0步的话，就省的往后尝试了
            if (nums[i] == 0) {
                continue;
            }
            // 从当前节点能走到的最远节点
            final int maxIndex = Math.min(i + nums[i], n - 1);
            for (int j = i; j <= maxIndex; j++) {
                // 尝试更新从i节点能走到的所有节点所需的最小步数
                // 一定要么是原来的小，要么是在dp[i]基础上走一步小
                dp[j] = Math.min(dp[j], dp[i] + 1);
            }
        }
        return dp[n - 1];
    }

    public static void main(String[] args) {
        final Solution_dp solution = new Solution_dp();
        System.out.println(solution.jump(new int[]{2, 3, 1, 1, 4}));
        System.out.println(solution.jump(new int[]{2, 3, 0, 1, 4}));
        System.out.println(solution.jump(new int[]{1}));
        System.out.println(solution.jump(new int[]{0}));
        System.out.println(solution.jump(new int[]{1,2}));
    }
}
