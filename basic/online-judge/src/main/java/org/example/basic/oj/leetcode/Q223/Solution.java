package org.example.basic.oj.leetcode.Q223;

/**
 * @author anyuan
 * @since 2021-09-30 13:25
 */
class Solution {
    /**
     * 求两个矩形的总面积（不重复）
     * <p>
     * 思路：实际上就是计算两个矩形各自的面积的和，然后减去两个矩形相交的部分
     *
     * @param ax1
     * @param ay1
     * @param ax2
     * @param ay2
     * @param bx1
     * @param by1
     * @param bx2
     * @param by2
     * @return
     */
    public int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        int area = (ax2 - ax1) * (ay2 - ay1) + (bx2 - bx1) * (by2 - by1);
        final int length = Math.min(ax2, bx2) - Math.max(ax1, bx1), width = (Math.min(ay2, by2) - Math.max(ay1, by1));
        int overlap = length < 0 || width < 0 ? 0 : length * width;
        return area - overlap;
    }

    public int computeArea_better(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        int length = Math.max(0, Math.min(ax2, bx2) - Math.max(ax1, bx1));
        int width = Math.max(0, Math.min(ay2, by2) - Math.max(ay1, by1));
        int sumArea = (ax2 - ax1) * (ay2 - ay1) + (bx2 - bx1) * (by2 - by1);
        return sumArea - length * width;
    }
}