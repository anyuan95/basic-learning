package org.example.basic.oj.leetcode.Q598;

/**
 * @author anyuan
 * @since 2021-11-07 22:23
 */
class Solution {
    public int maxCount(int m, int n, int[][] ops) {
        if (ops.length == 0) {
            return m * n;
        }
        int xMin = Integer.MAX_VALUE, yMin = Integer.MAX_VALUE;
        for (int[] op : ops) {
            xMin = Math.min(xMin, op[0]);
            yMin = Math.min(yMin, op[1]);
        }
        return xMin * yMin;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.maxCount(3, 3, new int[][]{{2, 2}, {3, 3}}));
        System.out.println(solution.maxCount(3, 3, new int[][]{{2, 2}, {3, 3}, {3, 3}, {3, 3}, {2, 2}, {3, 3}, {3, 3}, {3, 3}, {2, 2}, {3, 3}, {3, 3}, {3, 3}}));
        System.out.println(solution.maxCount(3, 3, new int[][]{}));
    }
}
