package org.example.basic.oj.leetcode.Q807;

/**
 * @author anyuan
 * @since 2021-09-27 15:42
 */
class Solution {

    public int maxIncreaseKeepingSkyline(int[][] grid) {
        // 先计算出原始数组两个维度的天际线高度
        final int m = grid.length, n = grid[0].length;
        int[] xWise = new int[m], yWise = new int[n];
        for (int x = 0; x < m; x++) {
            for (int y = 0; y < n; y++) {
                xWise[x] = Math.max(xWise[x], grid[x][y]);
                yWise[y] = Math.max(yWise[y], grid[x][y]);
            }
        }

        // 从两个方向找到最短高度
        int answer = 0;
        for (int x = 0; x < m; x++) {
            for (int y = 0; y < n; y++) {
                answer += Math.min(xWise[x], yWise[y]) - grid[x][y];
            }
        }
        return answer;
    }
}
