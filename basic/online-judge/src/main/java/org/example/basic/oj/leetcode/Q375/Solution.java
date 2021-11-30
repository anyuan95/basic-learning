package org.example.basic.oj.leetcode.Q375;

/**
 * @author anyuan
 * @since 2021-11-30 23:50
 */
class Solution {
    /**
     * 暴力的记忆化搜索
     * 实际上难点在于如何理解题意
     * 题目的求法实际上就是，在每种范围上计算猜中最少要花多少钱
     *
     * @param n
     * @return
     */
    public int getMoneyAmount(int n) {
        return dfs(1, n);
    }

    private int dfs(int l, int r) {
        if (l >= r) {
            return 0;
        }
        if (cache[l][r] != 0) {
            return cache[l][r];
        }
        int answer = Integer.MAX_VALUE;
        for (int i = l; i <= r; i++) {
            answer = Math.min(answer, Math.max(dfs(l, i - 1), dfs(i + 1, r)) + i);
        }
        cache[l][r] = answer;
        return answer;
    }

    private static int N = 210;
    private static int[][] cache = new int[N][N];
}
