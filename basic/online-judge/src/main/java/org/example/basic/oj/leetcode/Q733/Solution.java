package org.example.basic.oj.leetcode.Q733;

import java.util.Arrays;

/**
 * @author anyuan
 * @since 2021-08-19 16:52
 */
class Solution {
    /**
     * 感染问题
     *
     * @param image
     * @param sr
     * @param sc
     * @param newColor
     * @return
     */
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        infect(image, sr, sc, image[sr][sc], newColor);
        return image;
    }

    private void infect(int[][] image, int sr, int sc, int oldColor, int newColor) {
        if (sr < 0 || sr >= image.length || sc < 0 || sc >= image[0].length
                || image[sr][sc] != oldColor || image[sr][sc] == newColor) {
            return;
        }
        image[sr][sc] = newColor;
        infect(image, sr - 1, sc, oldColor, newColor);
        infect(image, sr + 1, sc, oldColor, newColor);
        infect(image, sr, sc - 1, oldColor, newColor);
        infect(image, sr, sc + 1, oldColor, newColor);
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        int[][] image = new int[][]{
                {0, 0, 0}, {0, 1, 1}
        };
        System.out.println(Arrays.deepToString(solution.floodFill(image, 1, 1, 1)));
    }

}
