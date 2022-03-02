package org.example.basic.oj.leetcode.Q1791;

import java.util.HashMap;
import java.util.Map;

/**
 * @author anyuan
 * @date 2022-02-18 11:40
 */
class Solution {
    /**
     * 有一个无向的 星型 图，由 n 个编号从 1 到 n 的节点组成。星型图有一个 中心 节点，并且恰有 n - 1 条边将中心节点与其他每个节点连接起来。
     * <p>
     * 给你一个二维整数数组 edges ，其中 edges[i] = [ui, vi] 表示在节点 ui 和 vi 之间存在一条边。请你找出并返回 edges 所表示星型图的中心节点。
     * <p>
     * 3 <= n <= 105
     * edges.length == n - 1
     * edges[i].length == 2
     * 1 <= ui, vi <= n
     * ui != vi
     * 题目数据给出的 edges 表示一个有效的星型图
     * <p>
     * <p>
     * 思考：
     * 题目明确说明给定的图一定是符合要求的，那么点answer一定会出现在n-1个点对中
     * 又因为数据范围有edges.length == n - 1，那么以说answer一定会出现在所有点对中
     * 现在缺少的条件是：所有ui-vi对都不重复
     * 假定满足该条件，则edges数组结构应该是[p1, answer], [p2, answer]...[pn-1, answer]
     * 即：
     * 1.每个点对中，都会有answer点
     * 2.每个点对中，非answer的点的值都不一样
     *
     * @param edges
     * @return
     */
    public int findCenter(int[][] edges) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                final int currentCount = count.getOrDefault(edges[i][j], 0) + 1;
                if (currentCount == 2) {
                    return edges[i][j];
                }
                count.put(edges[i][j], currentCount);
            }
        }
        return -1;
    }
}
