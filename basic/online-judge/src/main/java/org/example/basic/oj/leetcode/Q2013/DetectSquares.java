package org.example.basic.oj.leetcode.Q2013;

/**
 * point.length == 2
 * 0 <= x, y <= 1000
 * 调用 add 和 count 的 总次数 最多为 5000
 * <p>
 * 思考：
 * - 初步想到的方式有两种
 * - 一种是提前计算好每三个节点，然后count时只需要判断是否存在即可
 * - 一种是每次count时才进行计算
 */
class DetectSquares {

    // 记录每个点的数量
    final int[][] rcs = new int[1001][1001];

    public DetectSquares() {
    }

    public void add(int[] point) {
        this.rcs[point[0]][point[1]]++;
    }

    /**
     * 给你一个查询点，请你从数据结构中选出三个点，使这三个点和查询点一同构成一个 面积为正 的 轴对齐正方形 ，统计 满足该要求的方案数目。
     * <p>
     * 思考：
     * - 题目要求面积为正，即不统计面积为0的情况，即同一个位置上有不少于4个节点的情况不进行计算
     * - 题目要求轴对齐正方形，那么只需要根据给定的点，找到横坐标或纵坐标相同的所有点进行判断
     * <p>
     * 挺蠢的，换个思路
     * - 因为必须是轴对齐正方形，那么就是说斜率只会是1或者-1，那我直接找四个象限的点就好了
     * <p>
     * 统计与指定点能够组成正方形的个数
     *
     * @param point
     * @return
     */
    public int count(int[] point) {
        final int x = point[0], y = point[1];
        int count = 0;
        // y = kx + b, k = 1 OR -1
        // 先计算斜率=1的
        // y = x + b1
        final int b1 = y - x;
        for (int x1 = 0; x1 <= 1000; x1++) {
            int y1 = x1 + b1;
            if (y1 < 0 || y1 > 1000 || rcs[x1][y1] == 0 || x1 == x) {
                continue;
            }
            if (rcs[x1][y] != 0 && rcs[x][y1] != 0) {
                count += rcs[x1][y1] * rcs[x1][y] * rcs[x][y1];
            }
        }
        // 再计算斜率=-1的
        // y = -x + b2
        final int b2 = y + x;
        for (int x2 = 0; x2 <= 1000; x2++) {
            int y2 = b2 - x2;
            if (y2 < 0 || y2 > 1000 || rcs[x2][y2] == 0 || x2 == x) {
                continue;
            }
            if (rcs[x2][y] != 0 && rcs[x][y2] != 0) {
                count += rcs[x2][y2] * rcs[x2][y] * rcs[x][y2];
            }
        }
        return count;
    }

    public static void main(String[] args) {
        final DetectSquares detectSquares = new DetectSquares();
        detectSquares.add(new int[]{3, 10});
        detectSquares.add(new int[]{11, 2});
        detectSquares.add(new int[]{3, 2});
        System.out.println(detectSquares.count(new int[]{11, 10}));
        System.out.println(detectSquares.count(new int[]{14, 8}));
        detectSquares.add(new int[]{11, 2});
        System.out.println(detectSquares.count(new int[]{11, 10}));
    }


}