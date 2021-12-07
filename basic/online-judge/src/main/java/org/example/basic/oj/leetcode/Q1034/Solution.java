package org.example.basic.oj.leetcode.Q1034;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author anyuan
 * @date 2021-12-07 21:51
 */
class Solution {
    /**
     * 边界染色
     * bfs
     *
     * @param grid
     * @param row
     * @param col
     * @param color
     * @return
     */
    public int[][] colorBorder(int[][] grid, int row, int col, int color) {
        final Queue<int[]> queue = new LinkedList<>();
        final int m = grid.length, n = grid[0].length;
        final int[][] copied = new int[m][n];
        for (int i = 0; i < m; i++) {
            System.arraycopy(grid[i], 0, copied[i], 0, n);
        }
        boolean[][] visited = new boolean[m][n];
        int newColor = grid[row][col];

        queue.add(new int[]{row, col});
        while (!queue.isEmpty()) {
            final int size = queue.size();
            for (int i = 0; i < size; i++) {
                final int[] poll = queue.poll();
                // 如果当前节点已经处理过，那就标记上
                if (visited[poll[0]][poll[1]]) {
                    continue;
                }
                // 看一下弹出来的节点，是不是边界
                // 然后把周围所有相连的同色点加到队列中
                int sameColorNeighbourCount = 0;
                for (int[] direction : DIRECTIONS) {
                    final int newX = poll[0] + direction[0];
                    final int newY = poll[1] + direction[1];
                    if (newX >= 0 && newX < m && newY >= 0 && newY < n && grid[newX][newY] == newColor) {
                        // 如果当前节点连接的节点，颜色对
                        sameColorNeighbourCount++;
                        queue.add(new int[]{newX, newY});
                    }
                }
                // 如果周围四个不都是同色，那当前节点就是边界
                if (sameColorNeighbourCount < 4) {
                    copied[poll[0]][poll[1]] = color;
                }
                visited[poll[0]][poll[1]] = true;
            }
        }
        return copied;
    }

    private static final int[][] DIRECTIONS = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) {
        final Solution solution = new Solution();
//        System.out.println(Arrays.deepToString(solution.colorBorder(new int[][]{{1, 2, 2}, {2, 3, 2}}, 0, 1, 3)));
//        System.out.println(Arrays.deepToString(solution.colorBorder(new int[][]{{1, 1}, {1, 2}}, 0, 0, 3)));
//        System.out.println(Arrays.deepToString(solution.colorBorder(new int[][]{{1, 1, 1}, {1, 1, 1}, {1, 1, 1}}, 1, 1, 2)));
        System.out.println(Arrays.deepToString(solution.colorBorder(new int[][]{{1, 2, 1, 2, 1, 2}, {2, 2, 2, 2, 1, 2}, {1, 2, 2, 2, 1, 2}}, 1, 3, 1)));
    }

}

// 121212
// 222212
// 122212

// 111112
// 121112
// 111112
