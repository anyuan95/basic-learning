package org.example.basic.oj.leetcode.Q1091;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定一个由0和1组成的二维数组，现要求从0,0走到n-1,n-1，要求可以走8个方向，但只能走0的格子，求最短路径
 *
 * @author anyuan
 * @since 2021-09-06 17:05
 */
class Solution_1dArray {

    private static int len;

    public int shortestPathBinaryMatrix(int[][] grid) {
        final int n = grid.length;
        len = n;
        if (grid[0][0] == 1 || grid[n - 1][n - 1] == 1) {
            return -1;
        }
        int step = 0;
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n * n];
        queue.add(0);

        while (!queue.isEmpty()) {
            final int currentStepNodes = queue.size();
            step++;
            for (int i = 0; i < currentStepNodes; i++) {
                final Integer node = queue.poll();
                final int x = x(node), y = y(node);
                if (x == n - 1 && y == n - 1) {
                    return step;
                }
                // 剪掉遍历过的节点
                if (visited[node]) {
                    continue;
                }
                visited[node] = true;
                // 向周围8个方向走
                // 上
                if (x > 0 && grid[x - 1][y] == 0 && !visited[indexOf(x - 1, y)]) {
                    queue.add(indexOf(x - 1, y));
                }
                // 下
                if (x < n - 1 && grid[x + 1][y] == 0 && !visited[indexOf(x + 1, y)]) {
                    queue.add(indexOf(x + 1, y));
                }
                // 左
                if (y > 0 && grid[x][y - 1] == 0 && !visited[indexOf(x, y - 1)]) {
                    queue.add(indexOf(x, y - 1));
                }
                // 右
                if (y < n - 1 && grid[x][y + 1] == 0 && !visited[indexOf(x, y + 1)]) {
                    queue.add(indexOf(x, y + 1));
                }
                // 左上
                if (x > 0 && y > 0 && grid[x - 1][y - 1] == 0 && !visited[indexOf(x - 1, y - 1)]) {
                    queue.add(indexOf(x - 1, y - 1));
                }
                // 右上
                if (x > 0 && y < n - 1 && grid[x - 1][y + 1] == 0 && !visited[indexOf(x - 1, y + 1)]) {
                    queue.add(indexOf(x - 1, y + 1));
                }
                // 左下
                if (x < n - 1 && y > 0 && grid[x + 1][y - 1] == 0 && !visited[indexOf(x + 1, y - 1)]) {
                    queue.add(indexOf(x + 1, y - 1));
                }
                // 右下
                if (x < n - 1 && y < n - 1 && grid[x + 1][y + 1] == 0 && !visited[indexOf(x + 1, y + 1)]) {
                    queue.add(indexOf(x + 1, y + 1));
                }
            }
        }
        return -1;
    }

    private int indexOf(int x, int y) {
        return x * len + y;
    }

    private int x(int index) {
        return index / len;
    }

    private int y(int index) {
        return index % len;
    }

    public static void main(String[] args) {
        final Solution_1dArray solution = new Solution_1dArray();
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
