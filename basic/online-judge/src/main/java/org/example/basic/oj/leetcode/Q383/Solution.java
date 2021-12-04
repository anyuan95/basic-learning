package org.example.basic.oj.leetcode.Q383;

/**
 * @author anyuan
 * @date 2021-12-04 22:55
 */
class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        if (ransomNote.length() > magazine.length()) {
            return false;
        }
        final int[] occur = new int[26];
        for (char c : magazine.toCharArray()) {
            occur[c - 'a']++;
        }
        for (char c : ransomNote.toCharArray()) {
            if (occur[c - 'a'] == 0) {
                return false;
            }
            occur[c - 'a']--;
        }
        return true;
    }
}
