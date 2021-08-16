package org.example.basic.oj.zuo.greedy;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 股票问题
 * <p>
 * 输入：
 * 给定N个项目的花费和纯利润、最多可做的项目数量k、初始资金m。
 * <p>
 * 限制：
 * 1.你的资金必须达到项目的花费才能选择这个项目；
 * 2.给定的利润是纯利润，即扣除花费后的利润；
 * 3.最多只能做k个项目；
 * 4.同时只能做一个项目；
 * <p>
 * 输出：最后可获得的最大钱数
 * <p>
 *
 * @author anyuan
 * @since 2021-08-14 14:43
 */
class IPO {

    /**
     * 项目
     */
    static class Program {
        /**
         * 花费
         */
        int cost;
        /**
         * 纯利润
         */
        int profit;

        public Program(int cost, int profit) {
            this.cost = cost;
            this.profit = profit;
        }
    }

    /**
     * 思路：
     * 1.创建一个小根堆，使用项目的cost排序；
     * 2.创建一个大根堆，将当前剩余钱数能够参与的项目从小顶堆移动到大顶堆中，大顶堆使用profit排序；
     *
     * @param programs
     * @param k
     * @param m
     * @return
     */
    private static int ipo(List<Program> programs, int k, int m) {
        PriorityQueue<Program> minCostHeap = new PriorityQueue<>(Comparator.comparingInt(o -> o.cost));
        PriorityQueue<Program> maxProfitHeap = new PriorityQueue<>((o1, o2) -> o2.profit - o1.profit);
        minCostHeap.addAll(programs);
        for (int i = 0; i < k; i++) {
            while (!minCostHeap.isEmpty() && minCostHeap.peek().cost <= m) {
                maxProfitHeap.add(minCostHeap.poll());
            }
            if (maxProfitHeap.isEmpty()) {
                return m;
            }
            m += maxProfitHeap.poll().profit;
        }
        return m;
    }
}
