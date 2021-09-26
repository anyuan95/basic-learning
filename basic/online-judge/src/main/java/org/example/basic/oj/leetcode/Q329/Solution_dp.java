package org.example.basic.oj.leetcode.Q329;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author anyuan
 * @since 2021-09-26 14:16
 */
class Solution_dp {

    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    /**
     * 思路：dp+bfs?
     * ---------------------------
     * dp的思路：从最小值开始找，每次可以找到以最小值i结尾的最长递增序列。
     * 然后再找次最小的值，如果它周围有更小的值，那它的值就是四周更小的值中最小的一个+1。
     *
     * @param matrix
     * @return
     */
    public int longestIncreasingPath(int[][] matrix) {
        // 从最小值开始做bfs
        // bfs+记忆化搜索
        // 从值最小的点开始向周围找，不断记录和更新以该点结束的最长递增序列长度。
        final int m = matrix.length, n = matrix[0].length;
        // 记录从任意节点开始，到[m][n]最长的递增序列长度。这个过程中找到的所有最长长度一定是这个点的最长长度。
        int[][] dp = new int[m][n];

        // 先遍历一遍整个表，把所有值放到一个小顶堆中。
        PriorityQueue<Point> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));
        for (int x = 0; x < m; x++) {
            for (int y = 0; y < n; y++) {
                queue.offer(new Point(x, y, matrix[x][y]));
            }
        }

        // LISL means Longest Increasing Sequence Length
        int lisl = 1;

        // 每次从小顶堆中取出一个数，然后填这个数的LISL
        while (!queue.isEmpty()) {
            final Point currentPoint = queue.poll();
            // 这个点有可能在以前已经赋过值吗？不可能。因为是按值从小到大按顺序处理的。

            // 先给这个点赋初始值1（假如周围值都比他大，那它的LISL就只能是1了）
            dp[currentPoint.x][currentPoint.y] = 1;
            // 找周围的[填过的]且[值比当前点小的]
            for (int[] direction : DIRECTIONS) {
                int targetX = currentPoint.x + direction[0], targetY = currentPoint.y + direction[1];
                // 位置合法，且填过值了，且值比当前值小。那就可以计算current点的LISL了。
                if (targetX >= 0 && targetX < m && targetY >= 0 && targetY < n
                        && matrix[targetX][targetY] < currentPoint.val) {
                    // 反正是取和当前值比的最大值，就算没赋过值（0）也无所谓了。
                    dp[currentPoint.x][currentPoint.y] = Math.max(dp[currentPoint.x][currentPoint.y], dp[targetX][targetY] + 1);
                }
            }
            lisl = Math.max(lisl, dp[currentPoint.x][currentPoint.y]);
        }
        return lisl;
    }

    private static class Point {
        int x;
        int y;
        int val;

        public Point(int x, int y, int val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }
    }

}
