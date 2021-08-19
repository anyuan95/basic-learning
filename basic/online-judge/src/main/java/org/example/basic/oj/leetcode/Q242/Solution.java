package org.example.basic.oj.leetcode.Q242;

import java.util.Arrays;

/**
 * 判断s和p是否是异位词
 *
 * @author anyuan
 * @since 2021-08-19 17:27
 */
class Solution {
    public boolean isAnagram1(String s, String t) {
        final int sLength = s.length(), tLength = t.length();
        if (sLength != tLength) {
            return false;
        }
        final int[] tLettersAppearCount = new int[26], sLettersAppearCount = new int[26];
        for (int i = 0; i < tLength; i++) {
            tLettersAppearCount[t.charAt(i) - 'a']++;
            sLettersAppearCount[s.charAt(i) - 'a']++;
        }
        return Arrays.equals(tLettersAppearCount, sLettersAppearCount);
    }

    /**
     * 优化一下，不偷懒了，自己比较两个数组
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram2(String s, String t) {
        final int sLength = s.length(), tLength = t.length();
        if (sLength != tLength) {
            return false;
        }
        final int[] tLettersAppearCount = new int[26];
        for (int i = 0; i < tLength; i++) {
            tLettersAppearCount[t.charAt(i) - 'a']++;
        }
        for (int i = 0; i < sLength; i++) {
            tLettersAppearCount[s.charAt(i) - 'a']--;
            if (tLettersAppearCount[s.charAt(i) - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }
}
