package org.example.basic.oj.leetcode.Q1763;

/**
 * @author anyuan
 * @date 2022-02-01 20:38
 */
class Solution {
    /**
     * 用两个二进制数记录小写字母和大写字母的出现情况
     *
     * @param s
     * @return
     */
    public String longestNiceSubstring(String s) {
        int startIndex = -1, len = 0;
        final int n = s.length();
        for (int i = 0; i < n; i++) {
            int small = 0, big = 0;
            for (int j = i; j < n; j++) {
                final char c = s.charAt(j);
                if (c >= 'a' && c <= 'z') {
                    small |= (1 << (c - 'a'));
                } else {
                    big |= (1 << (c - 'A'));
                }
                if (small == big && j - i + 1 > len) {
                    len = j - i + 1;
                    startIndex = i;
                }
            }
        }
        return startIndex == -1 ? "" : s.substring(startIndex, startIndex + len);
    }
}
