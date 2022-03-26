package org.example.basic.oj.leetcode.Q1725;

class Solution {
    /**
     * 先求整个数组的最小值，然后计算数量
     *
     * @param rectangles
     * @return
     */
    public int countGoodRectangles(int[][] rectangles) {
        int maxLen = 0;
        for (int[] rectangle : rectangles) {
            maxLen = Math.max(Math.min(rectangle[0], rectangle[1]), maxLen);
        }

        int answer = 0;
        for (int[] rectangle : rectangles) {
            if (rectangle[0] >= maxLen && rectangle[1] >= maxLen) {
                answer++;
            }
        }
        return answer;
    }
}
