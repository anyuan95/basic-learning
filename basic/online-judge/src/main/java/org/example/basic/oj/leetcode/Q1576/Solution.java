package org.example.basic.oj.leetcode.Q1576;

/**
 * @author anyuan
 * @date 2022-01-05 22:11
 */
class Solution {
    public String modifyString(String s) {
        s = 'a' + s + 'a';
        final char[] chars = s.toCharArray();
        final int n = chars.length;
        for (int i = 1; i < n - 1; i++) {
            if (chars[i] == '?') {
                chars[i] = (char) ((chars[i - 1] - 'a' + 1) % 26 + 'a');
                if (chars[i] == chars[i+1]) {
                    chars[i] = (char) ((chars[i + 1] - 'a' + 1) % 26 + 'a');
                }
            }
        }
        return new String(chars, 1, n - 2);
    }
}
