package org.example.basic.oj.leetcode.Q851;

/**
 * @author anyuan
 * @date 2021-12-15 23:32
 */
class Solution {

    /**
     * 有一组 n 个人作为实验对象，从 0 到 n - 1 编号，其中每个人都有不同数目的钱，以及不同程度的安静值（quietness）。
     * 为了方便起见，我们将编号为 x 的人简称为 "person x "。
     *
     * 给你一个数组 richer ，其中 richer[i] = [ai, bi] 表示 person ai 比 person bi 更有钱。
     * 另给你一个整数数组 quiet ，其中 quiet[i] 是 person i 的安静值。richer 中所给出的数据 逻辑自恰
     * （也就是说，在 person x 比 person y 更有钱的同时，不会出现 person y 比 person x 更有钱的情况 ）。
     *
     * 现在，返回一个整数数组 answer 作为答案，其中 answer[x] = y 的前提是，
     * 在所有拥有的钱肯定不少于 person x 的人中，person y 是最安静的人（也就是安静值 quiet[y] 最小的人）。
     *
     * @param richer
     * @param quiet
     * @return
     */
    public int[] loudAndRich(int[][] richer, int[] quiet) {
        // 先调整数组结构
        final int n = quiet.length;
        // 应该是从小向大找
        // 0位置表示当前数组数量
        int[][] table = new int[n][n+1];
        for (int[] ints : richer) {
            final int bigger = ints[1];
            final int smaller = ints[0];
            table[smaller][table[smaller][0]] = bigger;
            table[smaller][0]++;
        }

        final int[] answer = new int[n];
        for (int i = 0; i < n; i++) {
            answer[i] = quiet[i];
        }

        // 不停计算最小值
        process(table, answer, 0);
        return answer;
    }

    private int process(int[][] table, int[] answer, int index) {
        final int n = answer.length;
        int minQuietIndex = answer[index];
        for (int i = index; i < n; i++) {
            final int len = table[i][0];
//            int targetIndex = table[index][];
            minQuietIndex = Math.min(process(table, answer, /*FIXME*/index), minQuietIndex);
        }

    }
}
