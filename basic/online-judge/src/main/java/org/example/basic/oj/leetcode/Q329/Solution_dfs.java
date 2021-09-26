package org.example.basic.oj.leetcode.Q329;

/**
 * @author anyuan
 * @since 2021-09-26 17:23
 */
class Solution_dfs {
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    /**
     * 思路：dp+dfs
     * ---------------------------
     *
     * @param matrix
     * @return
     */
    public int longestIncreasingPath(int[][] matrix) {
        final int m = matrix.length, n = matrix[0].length;
        // !!这里的dp含义和Solution_dp中的含义不一样！
        // 这里的dp表示的是，从[i][j]开始的所有递增序列中，最长的一个的长度
        int[][] dp = new int[m][n];
        int lisl = 1;
        // 使用记忆化搜索，可减少重复的搜索量
        for (int x = 0; x < m; x++) {
            for (int y = 0; y < n; y++) {
                if (dp[x][y] == 0) {
                    // dp==0说明没遍历过
                    dfs(matrix, dp, x, y);
                    lisl = Math.max(lisl, dp[x][y]);
                }
            }
        }
        return lisl;
    }

    private void dfs(int[][] matrix, int[][] dp, int x, int y) {
        final int m = matrix.length, n = matrix[0].length;
        if (dp[x][y] != 0) {
            // !=0说明遍历过
            return;
        }

        int lisl = 1;
        for (int[] direction : DIRECTIONS) {
            int targetX = x + direction[0], targetY = y + direction[1];
            if (targetX >= 0 && targetX < m && targetY >= 0 && targetY < n && matrix[targetX][targetY] > matrix[x][y]) {
                dfs(matrix, dp, targetX, targetY);
                lisl = Math.max(lisl, dp[targetX][targetY] + 1);
            }
        }
        dp[x][y] = lisl;
    }
}
