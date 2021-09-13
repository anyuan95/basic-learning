package org.example.basic.oj.leetcode.Q447;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author anyuan
 * @since 2021-09-13 10:57
 */
class Solution {
    /**
     * 题目理解：
     * 给定point数组，求能组成（i,j,k）满足|i,j|=|i,k|的三元组数量
     *
     * @param points
     * @return
     */
    public int numberOfBoomerangs(int[][] points) {
        final int n = points.length;
        if (n < 3) {
            // 最少需要3个点才能组成一个三元组
            return 0;
        }
        int answer = 0;
        // 先做暴力解，即求每个点到当前点的
        // key = 到当前点的距离，value = 离当前点距离=key的点的数量
        final HashMap<Integer, Integer> distanceMap = new HashMap<>();
        for (int[] from : points) {
            for (int j = 0; j < n; j++) {
                int[] to = points[j];
                final int dist = (from[1] - to[1]) * (from[1] - to[1]) + (from[0] - to[0]) * (from[0] - to[0]);
                // 这里再次进行优化，不存组成结果的信息了，反正最后答案只要数量。
                distanceMap.put(dist, distanceMap.getOrDefault(dist, 0) + 1);
            }
            // 现在xKey对应的list中的所有节点就是：以i节点为中心，以list(0)节点为一侧，以list(1)节点为另一侧 这样满足要求的三元组了
            // 把map里所有长度大于2的list都拿出来
            for (Integer sameDistancePointCount : distanceMap.values()) {
                // 因为有顺序要求，所以是排列（而不是组合）
                answer += sameDistancePointCount * (sameDistancePointCount - 1);
            }
            distanceMap.clear();
        }
        return answer;
    }

}
