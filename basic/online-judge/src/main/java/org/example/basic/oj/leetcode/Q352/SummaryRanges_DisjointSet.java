package org.example.basic.oj.leetcode.Q352;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author anyuan
 * @since 2021-10-09 14:36
 */
class SummaryRanges_DisjointSet {

    /**
     * key = 区间左值
     * value = 区间长度
     */
    private TreeMap<Integer, Integer> rangeMap;

    public SummaryRanges_DisjointSet() {
        this.rangeMap = new TreeMap<>();
    }

    public void addNum(int val) {
        if (!rangeMap.containsKey(val)) {
            // TODO 没懂为什么要放1
            rangeMap.put(val, 1);
            // 把val、val-1、val+1都尝试做一次合并
            merge(merge(rangeMap.floorKey(val - 1), val), rangeMap.ceilingKey(val + 1));
        }
    }

    private Integer merge(Integer pre, Integer cur) {
        if (pre == null && cur == null) {
            return null;
        } else if (pre == null || cur == null) {
            return pre == null ? cur : pre;
        } else if (pre + rangeMap.get(pre) >= cur) {
            // 如果pre的右边界大于等于cur的左边界，则说明二者可以合并

            // 取pre和cur所在范围的右边界中较大的一个
            rangeMap.put(pre, Math.max(pre + rangeMap.get(pre), cur + rangeMap.get(cur)) - pre);
            rangeMap.remove(cur);
            return pre;
        } else {
            return cur;
        }
    }

    public int[][] getIntervals() {
        final int[][] answer = new int[rangeMap.size()][2];
        int index = 0;
        for (Map.Entry<Integer, Integer> entry : rangeMap.entrySet()) {
            answer[index++] = new int[]{entry.getKey(), entry.getKey() + entry.getValue() - 1};
        }
        return answer;
    }
}
