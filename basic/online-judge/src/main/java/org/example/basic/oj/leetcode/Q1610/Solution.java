package org.example.basic.oj.leetcode.Q1610;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author anyuan
 * @since 2021-12-16 23:45
 */
class Solution {
    /**
     * 给你一个点数组 points 和一个表示角度的整数 angle ，你的位置是 location ，其中 location = [posx, posy] 且 points[i] = [xi, yi] 都表示 X-Y 平面上的整数坐标。
     * <p>
     * 最开始，你面向东方进行观测。你 不能 进行移动改变位置，但可以通过 自转 调整观测角度。换句话说，posx 和 posy 不能改变。你的视野范围的角度用 angle 表示， 这决定了你观测任意方向时可以多宽。设 d 为你逆时针自转旋转的度数，那么你的视野就是角度范围 [d - angle/2, d + angle/2] 所指示的那片区域。
     *
     *
     * 题目本身巨tm简单。。。
     * 就是到处都是细节
     *
     * 首先，这个范围可能是跨越了极线的
     * 然后，注意精度的处理
     * 再然后，注意和起始点重合的点
     *
     * 这题目的计算方式，实际上是把角度转换成了弧度
     *
     * @param points
     * @param angle
     * @param location
     * @return
     */
    public int visiblePoints(List<List<Integer>> points, int angle, List<Integer> location) {
        // 按照逆时针排序所有节点
        // 计算所有节点与x轴正向射线之间的夹角
        int samePointCount = 0;
        List<Double> degrees = new ArrayList<>();
        final Integer x = location.get(0);
        final Integer y = location.get(1);
        for (List<Integer> point : points) {
            final Integer x0 = point.get(0);
            final Integer y0 = point.get(1);
            if (x0 == x && y0 == y) {
                samePointCount++;
                continue;
            }
            degrees.add(Math.atan2(y0 - y, x0 - x));
        }
        Collections.sort(degrees);

        final int m = degrees.size();
        for (int i = 0; i < m; i++) {
            degrees.add(degrees.get(i) + 2 * Math.PI);
        }

        int maxCnt = 0;
        int rightIndex = 0;
        double toDegree = angle * Math.PI / 180;
        for (int i = 0; i < m; i++) {
            final double curr = degrees.get(i) + toDegree;
            while (rightIndex < degrees.size() && degrees.get(rightIndex) <= curr) {
                rightIndex++;
            }
            maxCnt = Math.max(maxCnt, rightIndex - i);
        }
        return maxCnt + samePointCount;
    }
}
