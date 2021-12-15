package org.example.basic.oj.leetcode.Q709;

/**
 * @author anyuan
 * @date 2021-12-12 02:08
 */
class Solution {
    public String toLowerCase(String s) {
        final char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] >= 'A' && chars[i] <='Z') {
                chars[i] = (char) (chars[i] - ('A' - 'a'));
            }
        }
        return new String(chars);
    }
}
