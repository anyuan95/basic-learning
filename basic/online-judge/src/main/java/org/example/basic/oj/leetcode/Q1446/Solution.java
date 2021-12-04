package org.example.basic.oj.leetcode.Q1446;

/**
 * @author anyuan
 * @since 2021-12-01 23:42
 */
class Solution {
    public int maxPower(String s) {
        final int n = s.length();
        int maxLen = 1, currLen = 1;
        char currChar = s.charAt(0);
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) == currChar) {
                currLen++;
            } else {
                maxLen = Math.max(maxLen,currLen);
                currLen = 1;
                currChar = s.charAt(i);
            }
        }
        maxLen = Math.max(maxLen,currLen);
        return maxLen;
    }
}
