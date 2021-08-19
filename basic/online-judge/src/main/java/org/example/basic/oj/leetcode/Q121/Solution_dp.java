package org.example.basic.oj.leetcode.Q121;

import java.util.Arrays;

/**
 * @author anyuan
 * @since 2021-08-19 09:28
 */
class Solution_dp {

    /**
     * 其实没有那么复杂，这就有点强行DP[]的意思了
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int[] dp = new int[prices.length];
        // i: 必须在i+1日卖  dp[i]: 必须在i+1日卖的前提下，能够获取的最大收益
        // 第1天卖，最大收益只能是0
        dp[0] = 0;
        int currentMin = prices[0];
        for (int i = 1; i < prices.length; i++) {
            dp[i] = Math.max(dp[i - 1], prices[i] - currentMin);
            currentMin = Math.min(currentMin, prices[i]);
        }
        return dp[prices.length - 1];
    }

    public static void main(String[] args) {
        int[] prices = new int[]{7,1,5,3,6,4};
        final Solution_dp solution = new Solution_dp();
        System.out.println(solution.maxProfit(prices));
    }
}
