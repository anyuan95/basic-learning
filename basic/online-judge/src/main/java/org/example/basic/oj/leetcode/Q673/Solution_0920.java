package org.example.basic.oj.leetcode.Q673;

import java.util.Arrays;

/**
 * @author anyuan
 * @since 2021-09-20 23:35
 */
class Solution_0920 {
    public int findNumberOfLIS(int[] nums) {
        final int n = nums.length;
        // dp[i]表示以nums[i]结尾的最长递增子序列长度
        int[] dp = new int[n];
        // 记录以i结尾的等长LIS有几个
        int[] sequences = new int[n];
        Arrays.fill(dp, 1);
        Arrays.fill(sequences, 1);

        int maxLen = 1;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                // 如果i能和j组成LIS，那dp[i]就可能是dp[j]+1
                if (nums[i] > nums[j]) {
                    // 这里dp[i]不能直接max，因为下面还要用当前的dp[i]做判断
                    if (dp[i] < dp[j] + 1) {
                        // 如果dp[i]<dp[j]+1，则一定要更新dp[i]。
                        // 相当于找出最长的能和i组合的那个j，这时候i的seq就是j的seq
                        dp[i] = dp[j] + 1;
                        sequences[i] = sequences[j];
                    } else if (dp[i] == dp[j] + 1) {
                        // 如果dp[i]=dp[j]+1，则说明找到一个和i拼在一起后和dp[i]等长的
                        // 就要把这个值也加到结果里
                        sequences[i] += sequences[j];
                    }
                }
            }
            maxLen = Math.max(maxLen, dp[i]);
        }
        int answer = 0;
        for (int i = 0; i < n; i++) {
            // 最后从dp数组中找到长度等于最长序列长度的所有下标位置，进行求和
            if (dp[i] == maxLen) {
                answer += sequences[i];
            }
        }
        return answer;
    }
}
