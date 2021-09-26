package org.example.basic.oj.leetcode.Q438;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author anyuan
 * @since 2021-09-23 10:50
 */
class Solution_0923 {
    public List<Integer> findAnagrams(String s, String p) {
        final int sLen = s.length(), pLen = p.length();
        if (sLen < pLen) {
            return Collections.emptyList();
        }

        List<Integer> answer = new ArrayList<>();
        int[] pattern = new int[26];
        int[] window = new int[26];
        for (int i = 0; i < pLen; i++) {
            pattern[p.charAt(i) - 'a']++;
            window[s.charAt(i) - 'a']++;
        }
        if (Arrays.equals(pattern, window)) {
            answer.add(0);
        }
        for (int i = pLen; i < sLen; i++) {
            // 加入i，去掉i-pLen
            window[s.charAt(i) - 'a']++;
            window[s.charAt(i - pLen) - 'a']--;

            if (Arrays.equals(pattern, window)) {
                answer.add(i - pLen + 1);
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        final Solution_0923 solution = new Solution_0923();
        System.out.println(solution.findAnagrams("cbaebabacd", "abc"));
        System.out.println(solution.findAnagrams("abab", "ab"));
    }
}
