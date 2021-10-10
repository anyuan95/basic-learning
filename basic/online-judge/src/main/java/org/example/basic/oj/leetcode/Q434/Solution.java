package org.example.basic.oj.leetcode.Q434;

/**
 * @author anyuan
 * @since 2021-10-07 23:39
 */
class Solution {
    public int countSegments(String s) {
        int wordCount = s.length() > 0 && s.charAt(0) != ' ' ? 1 : 0;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i-1) == ' ' && s.charAt(i) != ' ') {
                wordCount++;
            }
        }
        return wordCount;
    }
}
