package org.example.basic.oj.leetcode.Q438;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author anyuan
 * @since 2021-09-04 18:32
 */
class Solution_0904 {
    public List<Integer> findAnagrams(String s, String p) {
        final int pLen = p.toCharArray().length;
        final int sLen = s.toCharArray().length;
        List<Integer> answer = new ArrayList<>();
        if (pLen > sLen) {
            return answer;
        }

        int[] sDict = new int[26], pDict = new int[26];
        for (int i = 0; i < pLen; i++) {
            pDict[p.charAt(i) - 'a']++;
            sDict[s.charAt(i) - 'a']++;
        }
        if (Arrays.equals(pDict, sDict)) {
            answer.add(0);
        }
        for (int i = pLen; i < sLen; i++) {
            sDict[s.charAt(i - pLen) - 'a']--;
            sDict[s.charAt(i) - 'a']++;
            if (Arrays.equals(pDict, sDict)) {
                answer.add(i - pLen + 1);
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        final Solution_0904 solution = new Solution_0904();
        System.out.println(solution.findAnagrams("cbaebabacd", "abc"));
        System.out.println(solution.findAnagrams("abab", "ab"));
        System.out.println(solution.findAnagrams("aaaaaaaaaa", "aaaaaaaaaaaaa"));
    }
}