package org.example.basic.oj.leetcode.Q994;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 烂橘子
 * 给定一个数组，数组中0表示空地，1表示好橘子，2表示烂橘子，烂橘子的腐烂会每1秒传染上下左右一格。
 * 求最长要多久全部腐烂。
 *
 * @author anyuan
 * @since 2021-08-20 10:17
 */
class Solution {

    /**
     * 思路：
     * 1.首先记录所有烂橘子的位置放到队列中 和 好橘子的数量；
     * 2.然后从队列中逐个取出烂橘子开始发散，每一轮烂橘子将其上下左右进行传染，并将新腐烂的橘子加到队列中、将好橘子数量减少；
     * 3.直到所有烂橘子处理完，或是已经没有好橘子了为止。经过传染的总轮次就是最短时间；
     * 4.还有一种情况，队列已经空了但是还有好橘子，说明有无法传染的橘子，返回-1；
     *
     *
     * @param grid
     * @return
     */
    public int orangesRotting(int[][] grid) {
        int goodCount = 0;
        Queue<int[]> badQueue = new LinkedList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    goodCount++;
                } else if (grid[i][j] == 2) {
                    badQueue.add(new int[]{i, j});
                }
            }
        }

        // 轮次
        int round = 0;
        while (goodCount > 0 && !badQueue.isEmpty()) {
            round++;
            final int size = badQueue.size();
            for (int i = 0; i < size; i++) {
                final int[] currentPoint = badQueue.poll();
                final int x = currentPoint[0];
                final int y = currentPoint[1];
                if (x - 1 >= 0 && grid[x - 1][y] == 1) {
                    grid[x - 1][y] = 2;
                    goodCount--;
                    badQueue.add(new int[]{x - 1, y});
                }
                if (x + 1 < grid.length && grid[x + 1][y] == 1) {
                    grid[x + 1][y] = 2;
                    goodCount--;
                    badQueue.add(new int[]{x + 1, y});
                }
                if (y - 1 >= 0 && grid[x][y - 1] == 1) {
                    grid[x][y - 1] = 2;
                    goodCount--;
                    badQueue.add(new int[]{x, y - 1});
                }
                if (y + 1 < grid[0].length && grid[x][y + 1] == 1) {
                    grid[x][y + 1] = 2;
                    goodCount--;
                    badQueue.add(new int[]{x, y + 1});
                }
            }
        }
        return goodCount > 0 ? -1 : round;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        int[][] grid = new int[][]{
                {2, 1, 1}, {1, 1, 0}, {0, 1, 1}
        };
        System.out.println(solution.orangesRotting(grid));

    }
}
