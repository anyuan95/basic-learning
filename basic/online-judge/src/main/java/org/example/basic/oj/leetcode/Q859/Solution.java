package org.example.basic.oj.leetcode.Q859;

/**
 * @author anyuan
 * @date 2021-11-23 22:55
 */
class Solution {
    public boolean buddyStrings(String s, String goal) {
        final int sLen = s.length(), goalLen = goal.length();
        if (sLen != goalLen) {
            return false;
        }
        boolean hasRepeatChar = false;
        int[] sCount = new int[26];
        int diffIndex1 = -1, diffIndex2 = -1;
        for (int i = 0; i < sLen; i++) {
            if (s.charAt(i) != goal.charAt(i)) {
                if (diffIndex1 == -1) {
                    diffIndex1 = i;
                } else if (diffIndex2 == -1) {
                    diffIndex2 = i;
                } else {
                    return false;
                }
            }
            if (!hasRepeatChar) {
                sCount[s.charAt(i) - 'a']++;
                if (sCount[s.charAt(i) - 'a'] >= 2) {
                    hasRepeatChar = true;
                }
            }
        }
        if (diffIndex1 == -1 && diffIndex2 == -1) {
            return hasRepeatChar;
        }
    }
}
