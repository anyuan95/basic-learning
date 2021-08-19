package org.example.basic.oj.leetcode.Q695;

/**
 * 原来感染方式就是DFS...
 *
 * @author anyuan
 * @since 2021-08-19 14:03
 */
class Solution_infection {
    /**
     * 岛屿问题
     * <p>
     * 感染方式
     *
     * @param grid
     * @return
     */
    public int maxAreaOfIsland(int[][] grid) {
        int maxIslandArea = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    maxIslandArea = Math.max(maxIslandArea, infect(grid, i, j));
                }
            }
        }
        return maxIslandArea;
    }

    private int infect(int[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != 1) {
            return 0;
        }
        grid[i][j] = 2;
        return 1 + infect(grid, i - 1, j)
                + infect(grid, i + 1, j)
                + infect(grid, i, j - 1)
                + infect(grid, i, j + 1);
    }

    public static void main(String[] args) {
        final Solution_infection solution = new Solution_infection();
//        int[][] grid = new int[][]{
//                {0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
//                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
//                {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
//                {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
//                {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
//                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
//                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
//                {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}
//        };
        int[][] grid = new int[][]{
                {1, 1, 0, 0, 0},
                {1, 1, 0, 0, 0},
                {0, 0, 0, 1, 1},
                {0, 0, 0, 1, 1}
        };
        System.out.println(solution.maxAreaOfIsland(grid));
    }
}
