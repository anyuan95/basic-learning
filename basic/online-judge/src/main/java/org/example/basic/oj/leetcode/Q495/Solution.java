package org.example.basic.oj.leetcode.Q495;

/**
 * @author anyuan
 * @date 2021-11-10 14:17
 */
class Solution {
    public int findPoisonedDuration(int[] timeSeries, int duration) {
        int answer = 0;
        final int n = timeSeries.length;
        // 最后一发的
        answer += duration;
        for (int i = n - 2; i >= 0; i--) {
            answer += Math.min(timeSeries[i + 1] - timeSeries[i], duration);
        }
        return answer;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.findPoisonedDuration(new int[]{1, 4}, 2));
        System.out.println(solution.findPoisonedDuration(new int[]{1, 2}, 2));
    }
}
