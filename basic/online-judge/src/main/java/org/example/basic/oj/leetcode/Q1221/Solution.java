package org.example.basic.oj.leetcode.Q1221;

/**
 * @author anyuan
 * @since 2021-09-07 13:49
 */
class Solution {
    public int balancedStringSplit(String s) {
        final char[] chars = s.toCharArray();
        int answer = 0, rCount = 0, lCount = 0;
        for (char aChar : chars) {
            if (aChar == 'R') {
                rCount++;
            } else if (aChar == 'L') {
                lCount++;
            }
            if (rCount == lCount) {
                answer++;
                rCount = lCount = 0;
            }
        }
        return answer;
    }

    public static void main(String[] args) {

    }
}
