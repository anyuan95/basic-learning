package org.example.basic.oj.leetcode.Q2021;

/**
 * @author anyuan
 * @since 2022-01-01 20:08
 */
class Solution {
    public int[][] construct2DArray(int[] original, int m, int n) {
        final int len = original.length;
        if (m * n != len) {
            return new int[0][0];
        }
        final int[][] answer = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                answer[i][j] = original[n*i+j];
            }
        }
        return answer;
    }
}
