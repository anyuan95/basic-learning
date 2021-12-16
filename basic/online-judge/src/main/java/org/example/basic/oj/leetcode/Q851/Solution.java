package org.example.basic.oj.leetcode.Q851;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author anyuan
 * @date 2021-12-15 23:32
 */
class Solution {

    /**
     * 有一组 n 个人作为实验对象，从 0 到 n - 1 编号，其中每个人都有不同数目的钱，以及不同程度的安静值（quietness）。
     * 为了方便起见，我们将编号为 x 的人简称为 "person x "。
     * <p>
     * 给你一个数组 richer ，其中 richer[i] = [ai, bi] 表示 person ai 比 person bi 更有钱。
     * 另给你一个整数数组 quiet ，其中 quiet[i] 是 person i 的安静值。richer 中所给出的数据 逻辑自恰
     * （也就是说，在 person x 比 person y 更有钱的同时，不会出现 person y 比 person x 更有钱的情况 ）。
     * <p>
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

        List<Integer>[] lists = new ArrayList[n];
        for (int i = 0; i < lists.length; i++) {
            lists[i] = new ArrayList<>();
        }
        for (int[] ints : richer) {
            final int bigger = ints[0];
            final int smaller = ints[1];
            lists[smaller].add(bigger);
        }

        final int[] answer = new int[n];
        Arrays.fill(answer, -1);
        for (int i = 0; i < n; i++) {
            process(lists, quiet, answer, i);
        }
        return answer;
    }

    private void process(List<Integer>[] table, int[] quiet, int[] answer, int index) {
        if (answer[index] != -1) {
            return;
        }
        answer[index] = index;
        for (Integer bigger : table[index]) {
            process(table, quiet, answer, bigger);
            if (quiet[answer[bigger]] < quiet[answer[index]]) {
                answer[index] = answer[bigger];
            }
        }
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        final int[][] richer = {{1, 0}, {2, 1}, {3, 1}, {3, 7}, {4, 3}, {5, 3}, {6, 3}};
        final int[] quiet = {3, 2, 5, 4, 6, 1, 7, 0};
//        输出：[5,5,2,5,4,5,6,7]
        System.out.println(Arrays.toString(solution.loudAndRich(richer, quiet)));

    }
}
