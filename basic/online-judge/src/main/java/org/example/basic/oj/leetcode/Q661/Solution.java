package org.example.basic.oj.leetcode.Q661;

/**
 * @author anyuan
 * @date 2022-03-24 23:03
 */
class Solution {
    private final static int[][] DIRECTIONS = new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 0}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

    public int[][] imageSmoother(int[][] img) {
        final int m = img.length, n = img[0].length;
        final int[][] answer = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                answer[i][j] = greyAverage(img, i, j);
            }
        }
        return answer;
    }

    private int greyAverage(int[][] img, int x, int y) {
        final int m = img.length, n = img[0].length;
        int sum = 0, count = 0;
        for (int[] direction : DIRECTIONS) {
            final int newX = direction[0] + x;
            final int newY = direction[1] + y;
            if (newX >= 0 && newX < m && newY >= 0 && newY < n) {
                sum += img[newX][newY];
                count++;
            }
        }
        return sum / count;
    }
}
