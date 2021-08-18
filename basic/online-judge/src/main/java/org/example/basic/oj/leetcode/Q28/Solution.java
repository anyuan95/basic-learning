package org.example.basic.oj.leetcode.Q28;

/**
 * @author anyuan
 * @since 2021-08-18 17:38
 */
class Solution {
    /**
     * == indexOf
     *
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr(String haystack, String needle) {
        if (needle.length() == 0) {
            return 0;
        }

        final char[] chars1 = haystack.toCharArray();
        final char[] chars2 = needle.toCharArray();
        final int length1 = chars1.length;
        final int length2 = chars2.length;
        if (length1 < length2) {
            return -1;
        }
        outer: for (int i = 0; i < length1 - length2 + 1; i++) {
            if (chars1[i] == chars2[0]) {
                // 找s1中和s2[0]一致的位置，然后s2开始往后找
                for (int j = 1; j < length2; j++) {
                    if (chars1[i + j] != chars2[j]) {
                        continue outer;
                    }
                }
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.strStr("hello", "lo"));
        System.out.println(solution.strStr("aaaaa", "bba"));
        System.out.println(solution.strStr("", ""));
    }
}
