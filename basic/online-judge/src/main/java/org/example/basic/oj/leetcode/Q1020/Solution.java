package org.example.basic.oj.leetcode.Q1020;

class Solution {
    /**
     * 最简单的感染算法
     *
     * @param grid
     * @return
     */
    public int numEnclaves(int[][] grid) {
        int answer = 0;
        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];

        // 先对最外圈做一次感染
        for (int i = 0; i < m; i++) {
            if (!visited[i][0] && grid[i][0] == 1) {
                infect(i, 0, grid, visited);
            }
            if (!visited[i][n - 1] && grid[i][n - 1] == 1) {
                infect(i, n - 1, grid, visited);
            }
        }

        for (int j = 0; j < n; j++) {
            if (!visited[0][j] && grid[0][j] == 1) {
                infect(0, j, grid, visited);
            }
            if (!visited[m - 1][j] && grid[m - 1][j] == 1) {
                infect(m - 1, j, grid, visited);
            }
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (grid[i][j] == 1) {
                    answer++;
                }
            }
        }
        return answer;
    }

    private static final int[][] DIRECTIONS = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    private void infect(int x, int y, int[][] grid, boolean[][] visited) {
        grid[x][y] = 0;
        visited[x][y] = true;
        for (int[] direction : DIRECTIONS) {
            int newX = x + direction[0], newY = y + direction[1];
            if (newX >= 0 && newX < grid.length && newY >= 0 && newY < grid[0].length
                    && !visited[newX][newY] && grid[newX][newY] == 1) {
                grid[newX][newY] = 0;
                visited[newX][newY] = true;
                infect(newX, newY, grid, visited);
            }
        }
    }

    public static void main(String[] args) {
        //{{0,0,0,1,1,1,0,1,0,0},
        // {1,1,0,0,0,1,0,1,1,1},
        // {0,0,0,1,1,1,0,1,0,0},
        // {0,1,1,0,0,0,1,0,1,0},
        // {0,1,1,1,1,1,0,0,1,0},
        // {0,0,1,0,1,1,1,1,0,1},
        // {0,1,1,0,0,0,1,1,1,1},
        // {0,0,1,0,0,1,0,1,0,1},
        // {1,0,1,0,1,1,0,0,0,0},
        // {0,0,0,0,1,1,0,0,0,1}}
        final Solution solution = new Solution();
        System.out.println(solution.numEnclaves(new int[][]{{0, 0, 0, 1, 1, 1, 0, 1, 0, 0}, {1, 1, 0, 0, 0, 1, 0, 1, 1, 1}, {0, 0, 0, 1, 1, 1, 0, 1, 0, 0}, {0, 1, 1, 0, 0, 0, 1, 0, 1, 0}, {0, 1, 1, 1, 1, 1, 0, 0, 1, 0}, {0, 0, 1, 0, 1, 1, 1, 1, 0, 1}, {0, 1, 1, 0, 0, 0, 1, 1, 1, 1}, {0, 0, 1, 0, 0, 1, 0, 1, 0, 1}, {1, 0, 1, 0, 1, 1, 0, 0, 0, 0}, {0, 0, 0, 0, 1, 1, 0, 0, 0, 1}}));
    }
}
