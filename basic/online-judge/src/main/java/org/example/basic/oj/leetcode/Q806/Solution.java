package org.example.basic.oj.leetcode.Q806;

import java.util.Arrays;

class Solution {
    public int[] numberOfLines(int[] widths, String s) {
        int[] answer = new int[2];
        answer[0] = 1;
        for (char c : s.toCharArray()) {
            answer[1] += widths[c - 'a'];
            if (answer[1] > 100) {
                answer[0]++;
                answer[1] = widths[c - 'a'];
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.numberOfLines(new int[]{10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10}, "abcdefghijklmnopqrstuvwxyz")));
    }
}
