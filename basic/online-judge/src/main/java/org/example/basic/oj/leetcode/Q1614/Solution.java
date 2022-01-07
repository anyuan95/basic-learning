package org.example.basic.oj.leetcode.Q1614;

class Solution {
    public int maxDepth(String s) {
        final int n = s.length();
        final char[] chars = s.toCharArray();
        int maxDepth = 0, leftQuote = 0;
        for (char aChar : chars) {
            if (aChar == '(') {
                leftQuote++;
            } else if (aChar == ')') {
                leftQuote--;
            }
            maxDepth = Math.max(maxDepth, leftQuote);
        }
        return maxDepth;
    }
}
