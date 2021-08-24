package org.example.basic.oj.leetcode.Q787_unfinished;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * TODO 没完全看懂
 * @author anyuan
 * @since 2021-08-24 15:54
 */
class Solution_others {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        // Build the graph
        int mat[][] = new int[n][n];
        for (int flight[] : flights) {
            mat[flight[0]][flight[1]] = flight[2];
        }

        // min heap: {(vertex, cost, stops), ...}
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((e1, e2) -> e1[1] - e2[1]);

        // costs[i]: min cost from src to vertex i
        // stops[i]: number of stops of the corresponding cheapest cost for vertex i
        int costs[] = new int[n];
        int stops[] = new int[n];
        Arrays.fill(costs, Integer.MAX_VALUE);

        // Dijkstra Algorithm within k
        minHeap.offer(new int[]{src, 0, k});
        while (!minHeap.isEmpty()) {
            int elem[] = minHeap.poll();
            int vertex = elem[0], cost = elem[1], stop = elem[2];

            if (vertex == dst) {
                return cost;
            } else if (stop < 0) {
                continue;
            }

            for (int i = 0; i < n; ++i) {
                // 如果flights中存在从v到i的航班，则判断:
                // 如果从0到v的cost + 从v到i的cost < 当前记录的从0到i的最小cost，则将[i, 新的最小值, 步数-1]放到小顶堆中，并更新最小cost和剩余步数数组
                // ??? 如果最小cost的步数 < 当前步数-1，则将[i, 新的最小值, 步数-1]放到小顶堆中
                if (mat[vertex][i] > 0) {
                    int costI = costs[i], costVI = mat[vertex][i];
                    if (cost + costVI < costI) {
                        minHeap.offer(new int[]{i, costVI + cost, stop - 1});
                        costs[i] = costVI + cost;
                        stops[i] = stop - 1;
                    } else if (stops[i] < stop - 1) {
                        minHeap.offer(new int[]{i, costVI + cost, stop - 1});
                    }
                }
            }
        }
        return -1;
    }
}
