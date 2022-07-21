package org.example.basic.oj.leetcode.Q473;

import java.util.Arrays;

/**
 * @author anyuan
 * @date 2022-06-01 23:39
 */
class Solution {
    /**
     * 题目：给定int数组，将其平均分成四份
     *
     * 思路：分成四份，每份最大长度是avg。如果放进去当前值会导致超长，那就把当前值放到下一个堆里
     *
     * @param matchsticks
     * @return
     */
    public boolean makesquare(int[] matchsticks) {
        if (matchsticks.length < 4) {
            return false;
        }
        int sum = 0;
        for (int matchstick : matchsticks) {
            sum += matchstick;
        }
        if (sum % 4 != 0) {
            return false;
        }
        final int targetLen = sum / 4;
        Arrays.sort(matchsticks);
        return dfs(matchsticks, matchsticks.length - 1, targetLen, new int[4]);
    }

    private boolean dfs(int[] matchsticks, int pos, int targetLen, int[] sums) {
        if (pos == -1) {
            return sums[0] == targetLen && sums[1] == targetLen && sums[2] == targetLen;
        }
        for (int i = 0; i < 4; i++) {
            if (sums[i] + matchsticks[pos] > targetLen) {
                continue;
            }
            sums[i] += matchsticks[pos];
            if (dfs(matchsticks, pos-1, targetLen, sums)) {
                return true;
            }
            sums[i] -= matchsticks[pos];
        }
        return false;
    }
}
