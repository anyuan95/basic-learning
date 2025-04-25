package org.example.basic.oj.leetcode.Q2360;

import java.util.HashMap;
import java.util.Map;

class Solution {
    public int longestCycle(int[] edges) {
        // 每个节点一定有且只有一根指向其他节点的线
        // 理论上一个节点最多只可能在一个环中
        // 如果某个节点往下走，能回到自己的位置，那么就说明能组成一个环
        // 上一句遍历的过程中，也有可能出现：从当前节点a出发的路线上，某一个节点x重复出现了，这时说明从节点x开始组成了一个环，从a到x再到x路径上的所有节点都不需要再遍历了
        int n = edges.length;
        boolean[] viewed = new boolean[n];
        int max = -1;
        for (int i = 0; i < n; i++) {
            max = Math.max(dfs(edges, i, viewed), max);
        }
        return max;
    }

    private int dfs(int[] edges, int index, boolean[] viewed) {
        Map<Integer, Integer> indexMap = new HashMap<>();
        int depth = 0;
        while (true) {
            if (indexMap.containsKey(index)) {
                // 如果当前节点在当前回合遍历过，则说明找到环了，当前节点就是环的起点
                return depth - indexMap.get(index);
            }
            if (viewed[index]) {
                // 如果当前位置遍历过，则不需要再走了，如果有环的话之前一定计算过了
                return -1;
            }
            if (edges[index] == -1) {
                // 能走到没有出边的位置，说明一定没成环
                return -1;
            }
            // 否则就记录下当前节点深度，继续往下走
            indexMap.put(index, depth);
            depth++;
            viewed[index] = true;
            index = edges[index];
        }
    }
}