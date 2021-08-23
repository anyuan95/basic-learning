package org.example.basic.oj.leetcode.Q1646;

/**
 * @author anyuan
 * @since 2021-08-23 17:19
 */
class Solution {

    public int getMaximumGenerated(int n) {
        if (n < 2) {
            return n;
        }
        int[] answer = new int[n + 1];
        answer[0] = 0;
        answer[1] = 1;
        int max = 1;
        for (int i = 2; i <= n; i++) {
            answer[i] = answer[i / 2] + i % 2 * answer[i - i / 2];
            max = Math.max(max, answer[i]);
        }
        return max;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.getMaximumGenerated(65));
    }
}
