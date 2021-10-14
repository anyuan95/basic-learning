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
        // 先把第0行和第0列填上
        for (int i = 1; i < m; i++) {
            dp[i][0] = obstacleGrid[i][0] == 1 ? 0 : dp[i - 1][0];
        }
        for (int j = 1; j < n; j++) {
            dp[0][j] = obstacleGrid[0][j] == 1 ? 0 : dp[0][j - 1];
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] != 1) {
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

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.uniquePathsWithObstacles(new int[][]{{0, 0, 0}, {0, 1, 0}, {0, 0, 0}}));
        System.out.println(solution.uniquePathsWithObstacles(new int[][]{{0, 1}, {0, 0}}));
    }
}
