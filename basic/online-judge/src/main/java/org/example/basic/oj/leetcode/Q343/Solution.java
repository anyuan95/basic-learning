package org.example.basic.oj.leetcode.Q343;

/**
 * 将正整数x拆分成多个正整数，使得这些正整数的乘积尽可能地大
 *
 * @author anyuan
 * @since 2021-08-27 16:20
 */
class Solution {

    /**
     * dp[i]，表示i的最大拆分乘积
     *
     * @param n
     * @return
     */
    public int integerBreak(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 1;
//        dp[3] = 2;
//        dp[4] = 4;
//        dp[5] = 6;
//        dp[6] = 9;
//        dp[7] = 12;
//        dp[8] = 18;
//        dp[9] = 27;
//        dp[10] = 36;
//        dp[11] = 54;
//        dp[12] = 81;


        // 尽可能的拆成2和3？
        for (int i = 3; i <= n; i++) {
            dp[i] = Math.max(2 * Math.max(dp[i - 2], i - 2), 3 * Math.max(dp[i - 3], i - 3));
        }
        return dp[n];
    }
}
