package org.example.basic.oj.leetcode.Q1091;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author anyuan
 * @since 2021-09-06 17:05
 */
class Solution_2dArray {

    /**
     * 比一维数组速度快了一点，但是还是只有21%
     *
     * @param grid
     * @return
     */
    public int shortestPathBinaryMatrix(int[][] grid) {
        final int n = grid.length;
        if (grid[0][0] == 1 || grid[n - 1][n - 1] == 1) {
            return -1;
        }
        int step = 0;

        int[][] directions = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[n][n];
        queue.add(new int[]{0, 0});

        while (!queue.isEmpty()) {
            final int currentStepNodes = queue.size();
            step++;
            for (int i = 0; i < currentStepNodes; i++) {
                final int[] point = queue.poll();
                final int x = point[0], y = point[1];
                if (x == n - 1 && y == n - 1) {
                    return step;
                }
                // 剪掉遍历过的节点
                if (visited[x][y]) {
                    continue;
                }
                visited[x][y] = true;
                // 向周围8个方向走
                for (int[] direction : directions) {
                    int newX = x + direction[0];
                    int newY = y + direction[1];
                    if (newX >= 0 && newX < n && newY >= 0 && newY < n && grid[newX][newY] == 0 && !visited[newX][newY]) {
                        queue.add(new int[]{newX, newY});
                    }
                }

//                // 上
//                if (x > 0 && grid[x - 1][y] == 0 && !visited[x - 1][y]) {
//                    queue.add(new int[]{x - 1, y});
//                }
//                // 下
//                if (x < n - 1 && grid[x + 1][y] == 0 && !visited[x + 1][y]) {
//                    queue.add(new int[]{x + 1, y});
//                }
//                // 左
//                if (y > 0 && grid[x][y - 1] == 0 && !visited[x][y - 1]) {
//                    queue.add(new int[]{x, y - 1});
//                }
//                // 右
//                if (y < n - 1 && grid[x][y + 1] == 0 && !visited[x][y + 1]) {
//                    queue.add(new int[]{x, y + 1});
//                }
//                // 左上
//                if (x > 0 && y > 0 && grid[x - 1][y - 1] == 0 && !visited[x - 1][y - 1]) {
//                    queue.add(new int[]{x - 1, y - 1});
//                }
//                // 右上
//                if (x > 0 && y < n - 1 && grid[x - 1][y + 1] == 0 && !visited[x - 1][y + 1]) {
//                    queue.add(new int[]{x - 1, y + 1});
//                }
//                // 左下
//                if (x < n - 1 && y > 0 && grid[x + 1][y - 1] == 0 && !visited[x + 1][y - 1]) {
//                    queue.add(new int[]{x + 1, y - 1});
//                }
//                // 右下
//                if (x < n - 1 && y < n - 1 && grid[x + 1][y + 1] == 0 && !visited[x + 1][y + 1]) {
//                    queue.add(new int[]{x + 1, y + 1});
//                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        final Solution_2dArray solution = new Solution_2dArray();
        System.out.println(solution.shortestPathBinaryMatrix(new int[][]{
                {0, 1}, {1, 0}
        }));
        System.out.println(solution.shortestPathBinaryMatrix(new int[][]{
                {0, 0, 0}, {1, 1, 0}, {1, 1, 0}
        }));
        System.out.println(solution.shortestPathBinaryMatrix(new int[][]{
                {0, 1, 0}, {1, 1, 0}, {1, 1, 0}
        }));
    }
}
