package org.example.basic.oj.leetcode.Q673;

import java.util.Arrays;

/**
 * 求最长递增子序列个数
 *
 * @author anyuan
 * @since 2021-08-27 14:43
 */
class Solution {
    /**
     * 先试试dp
     * <p>
     * dp[i]表示以nums[i]结尾的最长递增子序列长度
     * <p>
     * longest increasing subsequences
     *
     * 思路：题目要求的是，和最长递增子序列长度相同的序列个数，而不是长度，
     * 所以添加一个数组去保存i位置时前面等长的LTS有几个
     *
     * @param nums
     * @return
     */
    public int findNumberOfLIS(int[] nums) {
        final int n = nums.length;
        int[] dp = new int[n];
        // 记录以i结尾的LIS有几个
        int[] sequences = new int[n];
        Arrays.fill(dp, 1);
        Arrays.fill(sequences, 1);

        int max = 1;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    if (dp[i] == dp[j] + 1) {
                        // 如果找到了和当前一样长的j，就把j的LIS个数加到i的上
                        sequences[i] += sequences[j];
                    } else if (dp[i] < dp[j] + 1){
                        // 如果找到了比当前还长的j，就取j的LIS个数，作为当前的个数
                        dp[i] = dp[j] + 1;
                        sequences[i] = sequences[j];
                    }
                }
            }
            max = Math.max(max, dp[i]);
        }
        int answer = 0;
        for (int j = 0; j < n; j++) {
            if (dp[j] == max) {
                answer += sequences[j];
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.findNumberOfLIS(new int[]{1, 3, 5, 4, 7}));
        System.out.println(solution.findNumberOfLIS(new int[]{2, 2, 2, 2, 2}));
    }
}
