package org.example.basic.oj.leetcode.Q686;

/**
 * @author anyuan
 * @date 2021-12-22 22:53
 */
class Solution {
    public int repeatedStringMatch(String a, String b) {
        final char[] aChars = a.toCharArray();
        final char[] bChars = b.toCharArray();
        boolean[] pattern = new boolean[26];
        for (char aChar : aChars) {
            pattern[aChar - 'a'] = true;
        }
        for (char bChar : bChars) {
            if (!pattern[bChar - 'a']) {
                return -1;
            }
        }
        final StringBuilder builder = new StringBuilder();
        final int count = (int) Math.floor(b.length() * 1.0 / a.length());
        for (int i = 0; i < count; i++) {
            builder.append(a);
        }
        if (builder.toString().contains(b)) {
            return count;
        }
        for (int i = 1; i < 3; i++) {
            builder.append(a);
            if (builder.toString().contains(b)) {
                return count + i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.repeatedStringMatch("abcd", "dabcda"));
    }
}
