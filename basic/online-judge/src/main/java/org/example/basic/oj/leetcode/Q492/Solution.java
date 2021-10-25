package org.example.basic.oj.leetcode.Q492;

import java.util.Arrays;

/**
 * @author anyuan
 * @date 2021-10-23 20:02
 */
class Solution {
    public int[] constructRectangle(int area) {
        int sqrt = (int) Math.sqrt(area);
        for (int i = sqrt; i > 0; i--) {
            if (area % i == 0) {
                return new int[]{area / i, i};
            }
        }
        return new int[]{area, 1};
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.constructRectangle(100)));
        System.out.println(Arrays.toString(solution.constructRectangle(99)));
    }
}
