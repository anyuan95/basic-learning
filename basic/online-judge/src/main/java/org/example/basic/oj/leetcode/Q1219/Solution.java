package org.example.basic.oj.leetcode.Q1219;

import java.util.Arrays;

class Solution {
    /**
     * 每个节点向外去做查找，从这个节点向外找到能获取最大价值的路线的价值和返回即可
     *
     * @param grid
     * @return
     */
    public int getMaximumGold(int[][] grid) {
        int answer = 0;
        final int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != 0) {
                    visited[i][j] = true;
                    answer = Math.max(answer, search(grid, visited, i, j));
                    visited[i][j] = false;
                }
            }
        }
        return answer;
    }

    private static final int[][] DIRECTIONS = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    private int search(int[][] grid, boolean[][] visited, int currentX, int currentY) {
        int maxValue = 0;
        for (int[] direction : DIRECTIONS) {
            final int x = currentX + direction[0];
            final int y = currentY + direction[1];
            if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length
                    && !visited[x][y] && grid[x][y] != 0) {
                visited[x][y] = true;
                maxValue = Math.max(maxValue, search(grid, visited, x, y));
                visited[x][y] = false;
            }
        }
        return maxValue + grid[currentX][currentY];
    }
}
