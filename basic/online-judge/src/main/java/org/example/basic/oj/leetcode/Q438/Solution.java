package org.example.basic.oj.leetcode.Q438;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 找到所有的异位词
 *
 * @author anyuan
 * @since 2021-08-19 17:14
 */
class Solution {

    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> answer = new ArrayList<>();
        final int sLength = s.length(), pLength = p.length();

        if (sLength < pLength) {
            return new ArrayList<>();
        }

        final char[] sChars = s.toCharArray(), pChars = p.toCharArray();
        final int[] pLettersAppearCount = new int[26], sLettersAppearCount = new int[26];

        for (int i = 0; i < pChars.length; i++) {
            pLettersAppearCount[pChars[i] - 'a']++;
            sLettersAppearCount[sChars[i] - 'a']++;
        }
        if (Arrays.equals(pLettersAppearCount, sLettersAppearCount)) {
            answer.add(0);
        }

        for (int startIndex = 1; startIndex <= sLength - pLength; startIndex++) {
            sLettersAppearCount[sChars[startIndex - 1] - 'a']--;
            sLettersAppearCount[sChars[startIndex + pLength - 1] - 'a']++;
            if (Arrays.equals(pLettersAppearCount, sLettersAppearCount)) {
                answer.add(startIndex);
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.findAnagrams("cbaebabacd", "abc"));
        System.out.println(solution.findAnagrams("abab", "ab"));
    }

}
