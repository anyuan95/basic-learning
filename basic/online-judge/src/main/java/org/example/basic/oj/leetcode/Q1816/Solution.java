package org.example.basic.oj.leetcode.Q1816;

/**
 * @author anyuan
 * @since 2021-12-06 23:45
 */
class Solution {
    public String truncateSentence(String s, int k) {
        final char[] chars = s.toCharArray();
        final int n = chars.length;
        int spaceCount = 0;
        for (int i = 0; i < n; i++) {
            if (chars[i] == ' ') {
                spaceCount++;
                if (spaceCount == k) {
                    return new String(chars, 0, i - 1);
                }
            }
        }
        return s;
    }
}
