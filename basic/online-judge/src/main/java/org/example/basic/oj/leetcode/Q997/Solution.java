package org.example.basic.oj.leetcode.Q997;

/**
 * @author anyuan
 * @since 2021-12-19 01:03
 */
class Solution {
    public int findJudge(int n, int[][] trust) {
        // 信任的数量
        int[] believes = new int[n+1];
        // 被信任的数量
        int[] believeds = new int[n+1];
        for (int[] ints : trust) {
            believes[ints[0]]++;
            believeds[ints[1]]++;
        }
        int judge = -1;
        for (int i = 1; i <= n; i++) {
            if (believes[i] == 0 && believeds[i] == n-1) {
                if (judge != -1) {
                    return -1;
                } else {
                    judge = i;
                }
            }
        }
        return judge;
    }
}
