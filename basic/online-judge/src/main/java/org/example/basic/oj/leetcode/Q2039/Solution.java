package org.example.basic.oj.leetcode.Q2039;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author anyuan
 * @date 2022-03-20 23:43
 */
class Solution {
    /**
     * 一个暴力的思路，求每个节点最后一次收到返回消息的时间
     *
     * @param edges
     * @param patience
     * @return
     */
    public int networkBecomesIdle(int[][] edges, int[] patience) {
        final int n = patience.length;

        final List<Integer>[] table = new ArrayList[n];
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            table[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            table[edge[0]].add(edge[1]);
            table[edge[1]].add(edge[0]);
        }

        int depth = 0;
        final int[] time = new int[n];
        final Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int cur = queue.poll();
                if (visited[cur]) {
                    continue;
                }
                visited[cur] = true;
                time[cur] = depth * 2;
                for (int index : table[cur]) {
                    queue.add(index);
                }
            }
            depth++;
        }

        int answer = 0;
        // 注意下标从1开始
        for (int i = 1; i < n; i++) {
            if (time[i] < patience[i]) {
                answer = Math.max(answer, time[i]);
            } else {
                int temp = (time[i] - 1) / patience[i] * patience[i] + time[i];
                answer = Math.max(answer, temp);
            }
        }
        return answer + 1;
    }
}
