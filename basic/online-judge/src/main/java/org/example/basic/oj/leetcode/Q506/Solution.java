package org.example.basic.oj.leetcode.Q506;

import java.util.PriorityQueue;

/**
 * @author anyuan
 * @since 2021-12-02 23:47
 */
class Solution {
    public static final String[] ONE_TWO_THREE = {"Gold Medal", "Silver Medal", "Bronze Medal"};

    public String[] findRelativeRanks(int[] score) {
        final PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> o2[0] - o1[0]);
        final int n = score.length;
        for (int i = 0; i < n; i++) {
            queue.add(new int[]{score[i], i});
        }
        final String[] answer = new String[n];
        for (int i = 0; i < n; i++) {
            final int[] poll = queue.poll();
            answer[poll[1]] = i < 3 ? ONE_TWO_THREE[i] : String.valueOf(i + 1);
        }
        return answer;
    }
}
