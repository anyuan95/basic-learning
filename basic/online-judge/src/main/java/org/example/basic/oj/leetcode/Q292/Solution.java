package org.example.basic.oj.leetcode.Q292;

/**
 * @author anyuan
 * @since 2021-09-18 09:19
 */
class Solution {
    /**
     * 巴什博奕
     * <p>
     * if n == 4*k，则先手必败，因为无论先手拿几个，后手都能让他变成4*(k-m)的状态，直到k==m时，后手就赢了
     * 其他情况下，先手必胜
     *
     * @param n
     * @return
     */
    public boolean canWinNim(int n) {
//        return n % 4 != 0;
        // 使用位运算做取模
        return (n & 3) != 0;
    }

    public boolean canWinNim_dp(int n) {
        if (n <= 3) {
            return true;
        }
        boolean[] dp = new boolean[n + 1];
        dp[1] = dp[2] = dp[3] = true;
        for (int i = 4; i <= n; i++) {
            // 如果剩m颗石子时输了，则剩m+1/m+2/m+3颗时一定能赢
            dp[i] = !dp[i - 1] || !dp[i - 2] || !dp[i - 3];
        }
        return dp[n];
    }
}
