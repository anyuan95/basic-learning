package org.example.basic.oj.leetcode.Q62;

/**
 * @author anyuan
 * @since 2021-09-14 23:41
 */
class Solution {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        // 记录到达每个点的走法
        dp[0][0] = 1;
        // 由于只能向下或者向右走，所以每个节点实际上最多只有两种走法
        // 先计算第一行
        for (int y = 1; y < n; y++) {
            dp[0][y] = 1;
        }
        // 再计算第一列
        for (int x = 1; x < m; x++) {
            dp[x][0] = 1;
        }
        // 再计算剩下的有合法左和上方向的
        for (int x = 1; x < m; x++) {
            for (int y = 1; y < n; y++) {
                dp[x][y] = dp[x - 1][y] + dp[x][y - 1];
            }
        }
        return dp[m - 1][n - 1];
    }
}
