package org.example.basic.oj.leetcode.Q200;

/**
 * 感染方式
 *
 * @author anyuan
 * @since 2021-09-06 09:35
 */
class Solution_infect {

    public int numIslands(char[][] grid) {
        int answer = 0;
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[x].length; y++) {
                if (grid[x][y] == '1') {
                    infect(grid, x, y);
                    answer++;
                }
            }
        }
        return answer;
    }

    private void infect(char[][] grid, int x, int y) {
        if (x < 0 || y < 0 || x >= grid.length || y >= grid[0].length) {
            return;
        }
        if (grid[x][y] == '1') {
            grid[x][y] = '2';
            infect(grid, x - 1, y);
            infect(grid, x + 1, y);
            infect(grid, x, y - 1);
            infect(grid, x, y + 1);
        }
    }
}
