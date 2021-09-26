package org.example.basic.oj.leetcode.Q1091;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author anyuan
 * @since 2021-09-26 10:55
 */
class Solution_0926 {

    private static int[][] DIRECTIONS = {{-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}};

    /**
     * 一个思路：从[0][0]开始往[n-1][n-1]走，走的过程中记录到达每个点的最短路径
     *
     * @param grid
     * @return
     */
    public int shortestPathBinaryMatrix(int[][] grid) {
        final int n = grid.length;
        if (grid[0][0] != 0 || grid[n - 1][n - 1] != 0) {
            return -1;
        }
        boolean[] visited = new boolean[n * n];
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(0);
        int steps = 0;
        while (!queue.isEmpty()) {
            final int size = queue.size();
            steps++;
            for (int i = 0; i < size; i++) {
                final Integer currentIndex = queue.poll();
                // 注意这个剪枝，这个剪枝是必需的。不然可能会出现由多个位置放射得到的目标点集有大量的交集。
                if (visited[currentIndex]) {
                    continue;
                }
                visited[currentIndex] = true;
                final int x = currentIndex / n, y = currentIndex % n;
                if (x == n - 1 && y == n - 1) {
                    return grid[x][y] == 0 ? steps : -1;
                }
                for (int[] position : DIRECTIONS) {
                    int targetX = x + position[0], targetY = y + position[1];
                    if (targetX >= 0 && targetX < n && targetY >= 0 && targetY < n && grid[targetX][targetY] == 0 && !visited[targetX * n + targetY]) {
                        queue.add(targetX * n + targetY);
                    }
                }
            }
        }
        return -1;
    }

//    private void process(int[][] grid, int[][] minSteps, int x, int y) {
//        if (x < 0 || x >= grid.length || y < 0 || y >= grid.length || grid[x][y] != 0) {
//            return;
//        }
//        if (x == grid.length - 1 && y == grid.length - 1) {
//            minSteps[x][y] = ;
//            return;
//        }
//        int minStep = INF;
//        // 向8个方向找
//        grid[x][y] += 100;
//        for (int[] position : POSITIONS) {
//
//
//            final Pair<Integer, Boolean> pair = process(grid, minSteps, x + position[0], y + position[1]);
//            if (pair.getValue()) {
//                minStep = Math.min(minStep, pair.getKey() + 1);
//            }
//        }
//        grid[x][y] -= 100;
//        return new Pair<>(minStep, minStep != INF);
//    }

    public static void main(String[] args) {
        final Solution_0926 solution = new Solution_0926();
        System.out.println(solution.shortestPathBinaryMatrix(new int[][]{{0, 0}}));
        System.out.println(solution.shortestPathBinaryMatrix(new int[][]{{0, 1}, {1, 0}}));
        System.out.println(solution.shortestPathBinaryMatrix(new int[][]{{0, 0, 0}, {1, 1, 0}, {1, 1, 0}}));
        System.out.println(solution.shortestPathBinaryMatrix(new int[][]{{1, 0, 0}, {1, 1, 0}, {1, 1, 0}}));
    }

}
