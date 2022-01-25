package org.example.basic.oj.leetcode.Q2045;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * @author anyuan
 * @date 2022-01-24 11:52
 */
class Solution {
    /**
     * 1.因为是稀疏图，所以用邻接矩阵存储更好
     * 2.整理计算逻辑，由于所有边和点的权重都是一样的，所以直接统计点的数量即可
     * 3.最简单最暴力的思路，求出1->n所有路径的点的数量，根据点的数量计算其所需的时间
     * --需要考虑到，这个图是可以有环的，所以拿到所有路径不现实，必须在这个思路的基础上剪枝
     * 4.例如，当只有两个点时，1-2一定是最短路径，1-2-1-2是次短路径。
     *
     * @param n
     * @param edges
     * @param time
     * @param change
     * @return
     */
    public int secondMinimum(int n, int[][] edges, int time, int change) {
        int[] level = new int[10001];
        int[] visited = new int[10001];
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] edge : edges) {
            graph.putIfAbsent(edge[0], new ArrayList<>());
            graph.putIfAbsent(edge[1], new ArrayList<>());
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        final Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        int ans = 0;
        int currentLevel = 0;
        while (!queue.isEmpty()) {
            currentLevel++;
            // 先计算当前需要的时间
            // 如果经历了奇数次边灯，则现在是红灯，需要等下一个绿灯
            if (ans / change % 2 == 1) {
                ans += change - ans % change;
            }
            ans += time;

            final int size = queue.size();
            for (int i = 0; i < size; i++) {
                final Integer poll = queue.poll();
                if (graph.containsKey(poll)) {
                    final List<Integer> nexts = graph.get(poll);
                    for (Integer next : nexts) {
                        if (next == n && level[next] != 0 && level[next] != currentLevel) {
                            return ans;
                        }
                        if (level[next] != currentLevel && visited[next] < 2) {
                            queue.add(next);
                            level[next] = currentLevel;
                            visited[next]++;
                        }
                    }
                }
            }
        }
        return -1;
    }
}
