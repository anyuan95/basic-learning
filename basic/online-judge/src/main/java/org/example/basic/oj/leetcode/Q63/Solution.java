package org.example.basic.oj.leetcode.Q63;

/**
 * @author anyuan
 * @date 2021-10-13 22:03
 */
class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        final int m = obstacleGrid.length;
        final int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = obstacleGrid[0][0] == 1 ? 0 : 1;
        for (int i = 0; i < m; i++) {
            if (obstacleGrid[i][0] == 1) {
                dp[i][0] = 0;
            }

        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                } else {
                    int ways = 0;
                    if (obstacleGrid[i-1][j] != 1) {
                        ways += dp[i-1][j];
                    }
                    if (obstacleGrid[i][j-1] != 1) {
                        ways += dp[i][j-1];
                    }
                    dp[i][j] = ways;
                }
            }
        }
        return dp[m-1][n-1];
    }
}
