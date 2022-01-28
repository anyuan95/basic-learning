package org.example.basic.oj.leetcode.Q2013;

import java.util.HashMap;
import java.util.Map;

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
class DetectSquares_Map {

    // 记录每个点的数量
    final Map<Integer, Map<Integer, Integer>> rcs = new HashMap<>();

    public DetectSquares_Map() {
    }

    public void add(int[] point) {
        rcs.putIfAbsent(point[0], new HashMap<>());
        final Map<Integer, Integer> subMap = rcs.get(point[0]);
        subMap.compute(point[1], (k, v) -> v == null ? 1 : v + 1);
    }

    /**
     * 改成map的需要注意的点在于，遍历的时候不再是遍历1000个位置，而是只需要检查keySet即可
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
        for (Integer x1 : rcs.keySet()) {
            int y1 = x1 + b1;
            if (y1 < 0 || y1 > 1000 || getCount(x1, y1) == 0 || x1 == x) {
                continue;
            }
            if (getCount(x1, y) != 0 && getCount(x, y1) != 0) {
                count += getCount(x1, y1) * getCount(x1, y) * getCount(x, y1);
            }
        }
        // 再计算斜率=-1的
        // y = -x + b2
        final int b2 = y + x;
        for (Integer x2 : rcs.keySet()) {
            int y2 = b2 - x2;
            if (y2 < 0 || y2 > 1000 || getCount(x2, y2) == 0 || x2 == x) {
                continue;
            }
            if (getCount(x2, y) != 0 && getCount(x, y2) != 0) {
                count += getCount(x2, y2) * getCount(x2, y) * getCount(x, y2);
            }
        }
        return count;
    }

    public Integer getCount(int x, int y) {
        if (rcs.containsKey(x)) {
            return rcs.get(x).getOrDefault(y, 0);
        }
        return 0;
    }

    public static void main(String[] args) {
        final DetectSquares_Map detectSquares = new DetectSquares_Map();
        detectSquares.add(new int[]{3, 10});
        detectSquares.add(new int[]{11, 2});
        detectSquares.add(new int[]{3, 2});
        System.out.println(detectSquares.count(new int[]{11, 10}));
        System.out.println(detectSquares.count(new int[]{14, 8}));
        detectSquares.add(new int[]{11, 2});
        System.out.println(detectSquares.count(new int[]{11, 10}));
    }


}