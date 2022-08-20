package org.example.basic.oj.leetcode.Q1422;

public class Solution {
    public int maxScore(String s) {
        final char[] chars = s.toCharArray();
        final int n = chars.length;
        int leftScore = 0, rightScore = 0;
        // 遍历2次，第一次拿到所有1的数量，即在0位置分割
        for (int i = 1; i < n - 1; i++) {
            if (chars[i] == '1') {
                rightScore++;
            }
        }

        int max = leftScore + rightScore;
        // 逐渐向右移动，计算移动过程中的最大值
        for (int i = 1; i < n-1; i++) {
            if (chars[i] == '0') {
                leftScore++;
            }
            if (chars[i] == '1') {
                rightScore--;
            }
            max = Math.max(max, leftScore + rightScore);
        }

        // 先计算中间的部分，最后再处理左和右
        if (chars[0] == '0') {
            max++;
        }
        if (chars[n - 1] == '1') {
            max++;
        }
        return max;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.maxScore("1111"));
        System.out.println(solution.maxScore("00"));
        System.out.println(solution.maxScore("011101"));
        System.out.println(solution.maxScore("00111"));
    }
}
