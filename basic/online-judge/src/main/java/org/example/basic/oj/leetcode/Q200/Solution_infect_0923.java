package org.example.basic.oj.leetcode.Q200;

/**
 * @author anyuan
 * @since 2021-09-23 16:05
 */
class Solution_infect_0923 {
    public int numIslands(char[][] grid) {
        int islandCount = 0;
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[x].length; y++) {
                if (grid[x][y] == '1') {
                    process(grid, x, y);
                    islandCount++;
                }
            }
        }
        return islandCount;
    }

    private void process(char[][] grid, int x, int y) {
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] != '1') {
            return;
        }
        grid[x][y] = '2';
        process(grid, x - 1, y);
        process(grid, x + 1, y);
        process(grid, x, y - 1);
        process(grid, x, y + 1);
    }

}
