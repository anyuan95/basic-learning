package org.example.basic.oj.leetcode.Q542_unfinished_dp;

/**
 * @author anyuan
 * @since 2021-08-20 15:41
 */
class Solution_DP {

    /**
     * DP应该就是每个节点都是四周节点的最小值+1
     * answer自己就是个dp表.........
     *
     * @param mat
     * @return
     */
    public int[][] updateMatrix(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] != 0) {
                    dp[i][j] = Integer.MAX_VALUE / 2;
                }
            }
        }


        return dp;
    }


}
