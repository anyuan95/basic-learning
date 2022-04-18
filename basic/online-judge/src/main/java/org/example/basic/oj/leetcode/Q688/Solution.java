package org.example.basic.oj.leetcode.Q688;

class Solution {

    private final static int[][] DIRECTIONS = new int[][]{{1, 2}, {1, -2}, {-1, -2}, {-1, 2}, {2, 1}, {2, -1}, {-2, -1}, {-2, 1}};

    public double knightProbability(int n, int k, int row, int column) {
        Double[][][] dp = new Double[k][n][n];
        return dfs(dp, n, k, row, column);
    }

    private double dfs(Double[][][] dp, int n, int k, int row, int column) {
        if (k == 0) {
            return 1;
        }
        if (dp[k][row][column] != null) {
            return dp[k][row][column];
        }
        double probability = 0;
        int count = 0;
        for (int[] direction : DIRECTIONS) {
            int newX = row + direction[0], newY = column + direction[1];
            if (newX >= 0 && newX < n && newY >= 0 && newY < n) {
                probability += dfs(dp, n, k - 1, newX, newY);
                count++;
            }
        }
        if (count > 0) {
            probability /= count;
            probability *= count / 8.0;

        }
        dp[k][row][column] = probability;
        return probability;
    }
}
