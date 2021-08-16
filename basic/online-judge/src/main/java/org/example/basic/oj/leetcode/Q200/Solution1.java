package org.example.basic.oj.leetcode.Q200;

/**
 * 岛屿问题
 *
 * @author anyuan
 * @since 2021-08-14 23:06
 */
class Solution1 {
    /**
     * 思路：使用感染方式，每当遍历到1节点时，将所有与之相连的1节点都感染成其他值。找到1节点的次数就是岛的数量。
     *
     * @param grid
     * @return
     */
    public int numIslands(char[][] grid) {
        int islandCount = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    inject(grid, i, j);
                    islandCount++;
                }
            }
        }
        return islandCount;
    }

    /**
     * 感染，将所有当前节点相邻的1都改成2
     */
    private void inject(char[][] grid, int i, int j) {
        if (i < 0 || i > grid.length || j < 0 || j > grid[0].length || grid[i][j] != '1') {
            return;
        }
        // 将当前节点改成感染状态
        grid[i][j] = '2';
        inject(grid, i - 1, j);
        inject(grid, i + 1, j);
        inject(grid, i, j - 1);
        inject(grid, i, j + 1);
    }

    public static void main(String[] args) {
        char[][] grid = {
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        };
        final Solution1 solution = new Solution1();
        System.out.println(solution.numIslands(grid));
    }

}
