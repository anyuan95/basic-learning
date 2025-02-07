package org.example.basic.oj.leetcode.Q1582;

class Solution {
    public int numSpecial(int[][] mat) {
        final int m = mat.length;
        final int n = mat[0].length;
        final int[] row = new int[m];
        final int[] column = new int[n];
        int answer = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                row[i] += mat[i][j];
                column[j] += mat[i][j];
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (row[i] == 1 && column[i] == 1 && mat[i][j] == 1) {
                    answer++;
                }
            }
        }
        return answer;
    }
}
