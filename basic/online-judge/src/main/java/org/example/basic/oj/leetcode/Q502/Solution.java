package org.example.basic.oj.leetcode.Q502;

import java.util.PriorityQueue;

/**
 * 给定n个项目，每个项目有成本cost和纯利润profit。给定原始资金w，允许最多做k个项目，求最后最大剩余资金（包括原始资金）。
 *
 * @author anyuan
 * @since 2021-09-08 14:20
 */
class Solution {
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        int n = profits.length;
        // 保存未选的所有项目，按照代价升序（代价小顶堆）
        PriorityQueue<Project> capitalMinHeap = new PriorityQueue<>((o1, o2) -> o1.capital - o2.capital);
        // 保存当前余额w下可选的所有项目，按照纯利润降序（利润大顶堆）
        PriorityQueue<Project> profitMaxHeap = new PriorityQueue<>((o1, o2) -> o2.profit - o1.profit);
        for (int i = 0; i < n; i++) {
            final Project project = new Project(profits[i], capital[i]);
            capitalMinHeap.offer(project);
        }

        // 从最大利润堆里取出小于w的里的价值最大的，去掉，然后价值加到w上，k--，最小代价堆也弹出这个项目
        for (int i = 0; i < k; i++) {
            while (!capitalMinHeap.isEmpty() && capitalMinHeap.peek().capital <= w) {
                profitMaxHeap.offer(capitalMinHeap.poll());
            }
            if (profitMaxHeap.isEmpty()) {
                return w;
            }
            w += profitMaxHeap.poll().profit;
        }
        return w;
    }

    private static class Project {
        int profit;
        int capital;

        public Project(int profit, int capital) {
            this.profit = profit;
            this.capital = capital;
        }
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.findMaximizedCapital(2, 0, new int[]{1, 2, 3}, new int[]{0, 1, 1}));
    }
}
