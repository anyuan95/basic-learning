package org.example.basic.oj.leetcode.Q149;

import java.util.*;

/**
 * @author anyuan
 * @since 2021-10-04 21:58
 */
class Solution {
    /**
     * 思路：
     * 首先是暴力解法
     * 将计算每两个点组成的直线的斜率，用能表示该直线的二元一次方程表示该直线。
     * 创建一个哈希表，key就是该直线，value是所有在该直线上的点。
     * <p>
     * 题目给定points不超过300个，300个元素互相握手，最多能够生成(1+299)*299/2个key。
     * 然后对于每个key，遍历300个point，查看哪些point在这个key上，进行记录。
     * <p>
     * 如果出现了多个点斜率相同，则放到同一个key的value里。
     *
     * @param points
     * @return
     */
    public int maxPoints(int[][] points) {
        // set里只保存是第几个点
        Map<Line, HashSet<Integer>> lineMap = new HashMap<>();
        for (int fromPointIndex = 0; fromPointIndex < points.length; fromPointIndex++) {
            final int[] fromPoint = points[fromPointIndex];
            for (int toPointIndex = fromPointIndex + 1; toPointIndex < points.length; toPointIndex++) {
                final int[] toPoint = points[toPointIndex];
                final Line line = Line.generateLine(fromPoint, toPoint);
                if (!lineMap.containsKey(line)) {
                    final HashSet<Integer> pointSet = new HashSet<>();
                    pointSet.add(fromPointIndex);
                    pointSet.add(toPointIndex);
                    lineMap.put(line, pointSet);
                } else {
                    final HashSet<Integer> pointSet = lineMap.get(line);
                    pointSet.add(fromPointIndex);
                    pointSet.add(toPointIndex);
                }
            }
        }

        // 然后再对照着map做一次遍历
        // 顺便找出最大值
        int maxPointsOnOneLine = 1;
        final Set<Map.Entry<Line, HashSet<Integer>>> entries = lineMap.entrySet();
        for (Map.Entry<Line, HashSet<Integer>> entry : entries) {
            final Line line = entry.getKey();
            final HashSet<Integer> pointsOnLine = entry.getValue();
            for (int pointIndex = 0; pointIndex < points.length; pointIndex++) {
                if (line.isInLine(points[pointIndex])) {
                    pointsOnLine.add(pointIndex);
                }
            }
            maxPointsOnOneLine = Math.max(maxPointsOnOneLine, pointsOnLine.size());
        }
        return maxPointsOnOneLine;
    }

    /**
     * y = kx + b
     * <p>
     * 注意：斜截式只能表示不垂直于x轴的直线
     */
    private static class Line {
        double k;
        double b;

        boolean isVertical;
        int x;

        public Line(double k, double b) {
            this.k = k;
            this.b = b;
            this.isVertical = false;
        }

        public Line(boolean isVertical, int x) {
            this.isVertical = isVertical;
            this.x = x;
        }

        static Line generateLine(int[] point1, int[] point2) {
            if (point1[0] == point2[0]) {
                return new Line(true, point1[0]);
            }
            double k = (point2[1] - point1[1]) / 1.0 / (point2[0] - point1[0]);
            double b = point1[1] - point1[0] * k;
            return new Line(k, b);
        }

        boolean isInLine(int[] point) {
            if (this.isVertical) {
                return point[0] == this.x;
            } else {
                return point[1] == this.k * point[0] + this.b;
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Line line = (Line) o;
            return k == line.k && b == line.b && isVertical == line.isVertical && x == line.x;
        }

        @Override
        public int hashCode() {
            return Objects.hash(k, b, isVertical, x);
        }
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.maxPoints(new int[][]{
                {0, 0}, {4, 5}, {7, 8}, {8, 9}, {5, 6}, {3, 4}, {1, 1}
        }));
    }
}
