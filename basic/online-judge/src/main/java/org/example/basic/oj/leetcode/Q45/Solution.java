package org.example.basic.oj.leetcode.Q45;

/**
 * @author anyuan
 * @since 2021-09-14 23:28
 */
class Solution {
    public int jump(int[] nums) {
        final int n = nums.length;
        if (n == 1) {
            return 0;
        }
        // 步数
        int step = 0,
                // 当前最大能到的位置
                maxCanReachIndex = 0,
                // 上一步最远能到达的位置
                lastStepMaxCanReachIndex = 0;
        for (int i = 0; i < n; i++) {
            // 思路：相当于记录每一步能够走到的最远位置
            // 计算方式：取当前步数能够走到的所有位置 能再到达的最远位置；当走到这个位置之后，将步数+1（因为当前步数最远只能到这个位置了），然后设置新的当前步数最远能到达位置
            maxCanReachIndex = Math.max(maxCanReachIndex, i + nums[i]);
            if (maxCanReachIndex >= n - 1) {
                return step + 1;
            }
            if (i == lastStepMaxCanReachIndex) {
                step++;
                lastStepMaxCanReachIndex = maxCanReachIndex;
            }
        }
        return step;
    }
}
