package org.example.basic.oj.leetcode.Q2045;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * @author anyuan
 * @date 2022-01-24 13:59
 */
class Solution_answer {

    static int N = 10010, M = 4 * N, INF = 0x3f3f3f3f, idx = 0;
    static int[] he = new int[N], e = new int[M], ne = new int[M];
    void add(int a, int b) {
        e[idx] = b;
        ne[idx] = he[a];
        he[a] = idx;
        idx++;
    }
    public int secondMinimum(int n, int[][] edges, int time, int change) {
        Arrays.fill(he, -1);
        idx = 0;
        for (int[] e : edges) {
            int u = e[0], v = e[1];
            add(u, v); add(v, u);
        }
        Deque<int[]> d = new ArrayDeque<>();
        int[] dist1 = new int[n + 10], dist2 = new int[n + 10];
        Arrays.fill(dist1, INF);
        Arrays.fill(dist2, INF);
        d.addLast(new int[]{1, 0});
        dist1[1] = 0;
        while (!d.isEmpty()) {
            int[] poll = d.pollFirst();
            int u = poll[0], dist = poll[1];
            for (int i = he[u]; i != -1; i = ne[i]) {
                int j = e[i];
                if (dist1[j] > dist + 1) {
                    dist2[j] = dist1[j];
                    dist1[j] = dist + 1;
                    d.addLast(new int[]{j, dist1[j]});
                    d.addLast(new int[]{j, dist2[j]});
                } else if (dist1[j] < dist + 1 && dist + 1 < dist2[j]) {
                    dist2[j] = dist + 1;
                    d.addLast(new int[]{j, dist2[j]});
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < dist2[n]; i++) {
            int a = ans / change, b = ans % change;
            int wait = a % 2 == 0 ? 0 : change - b;
            ans += time + wait;
        }
        return ans;
    }
}