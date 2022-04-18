package org.example.basic.oj.leetcode.Q1765;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    /**
     * 思路：bfs，从水坑开始向外发散
     *
     * @param isWater
     * @return
     */
    public int[][] highestPeak(int[][] isWater) {
        int height = 0;
        Queue<int[]> queue = new LinkedList<>();
        final int m = isWater.length, n = isWater[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (isWater[i][j] == 1) {
                    queue.add(new int[]{i, j});
                }
            }
        }
        final int[][] answer = new int[m][n];
        while (!queue.isEmpty()) {
            final int size = queue.size();
            height++;
            for (int i = 0; i < size; i++) {
                final int[] curr = queue.poll();
                final int x = curr[0], y = curr[1];
                for (int[] direction : DIRECTIONS) {
                    final int targetX = x + direction[0], targetY = y + direction[1];
                    if (targetX >= 0 && targetX < m && targetY >= 0 && targetY < n
                            && isWater[targetX][targetY] == 0 && answer[targetX][targetY] == 0) {
                        answer[targetX][targetY] = height;
                        queue.add(new int[]{targetX, targetY});
                    }
                }
            }
        }
        return answer;
    }

    private static final int[][] DIRECTIONS = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(Arrays.deepToString(solution.highestPeak(new int[][]{{0, 1}, {0, 0}})));
        System.out.println(Arrays.deepToString(solution.highestPeak(new int[][]{{0, 0, 1}, {1, 0, 0}, {0, 0, 0}})));
    }
}
