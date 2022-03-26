package org.example.basic.oj.leetcode.Q1706;

import java.util.Arrays;

class Solution {
    public int[] findBall(int[][] grid) {
        final int m = grid.length, n = grid[0].length;
        final int[] answer = new int[n];
        for (int i = 0; i < n; i++) {
            answer[i] = findFinalTarget(grid, 0, i);
        }
        return answer;
    }

    /**
     * 换个想法，x实际上是它当前在哪一行上面的横线上
     */
    private int findFinalTarget(int[][] grid, int x, int y) {
        // 如果已经走完最后一层了，那现在在哪就是哪了
        final int m = grid.length, n = grid[0].length;
        if (x == m) {
            return y;
        }
        if (x >= m || y >= n || x < 0 || y < 0) {
            System.out.println("?");
        }
        // 左上到右下
        // 往下走的前提条件是，右一格也是左上到右下
        if (grid[x][y] == 1) {
            // \| 或 \/
            if ((y + 1 < n && grid[x][y + 1] == -1) || y + 1 == n) {
                return -1;
            }
            return findFinalTarget(grid, x + 1, y + 1);
        } else /* if (grid[x][y] == -1) */ {
            // |/ 或 \/
            if ((y > 0 && grid[x][y - 1] == 1) || y == 0) {
                return -1;
            }
            return findFinalTarget(grid, x + 1, y - 1);
        }
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.findBall(new int[][]{{1, 1, 1, -1, -1}, {1, 1, 1, -1, -1}, {-1, -1, -1, 1, 1}, {1, 1, 1, 1, -1}, {-1, -1, -1, -1, -1}})));
    }
}
