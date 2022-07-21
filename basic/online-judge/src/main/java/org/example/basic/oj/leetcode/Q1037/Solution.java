package org.example.basic.oj.leetcode.Q1037;

/**
 * @author anyuan
 * @date 2022-06-08 23:55
 */
class Solution {

    public boolean isBoomerang(int[][] ps) {
        // 只要三个点不在一条线上即可
        // 等同于，三角形面积不为0即可
        return (ps[1][0] - ps[0][0]) * (ps[2][1] - ps[0][1]) != (ps[2][0] - ps[0][0]) * (ps[1][1] - ps[0][1]);
    }


    public int distance(int[] point1, int[] point2) {
        return (point1[0] - point2[0]) * (point1[0] - point2[0]) + (point1[1] - point2[1]) * (point1[1] - point2[1]);
    }
}
