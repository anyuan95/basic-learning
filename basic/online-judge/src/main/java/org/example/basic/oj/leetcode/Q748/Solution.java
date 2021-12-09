package org.example.basic.oj.leetcode.Q748;

import java.util.Arrays;
import java.util.Objects;
import java.util.PriorityQueue;

/**
 * @author anyuan
 * @since 2021-12-10 00:10
 */
class Solution {
    public String shortestCompletingWord(String licensePlate, String[] words) {
        int[] pattern = new int[26];
        for (char c : licensePlate.toCharArray()) {
            if (Character.isAlphabetic(c)) {
                pattern[Character.toLowerCase(c) - 'a']++;
            }
        }
        String answer = null;
        for (String word : words) {
            if (check(pattern, word)) {
                if (answer == null) {
                    answer = word;
                } else if (word.length() < answer.length()) {
                    answer = word;
                }
            }
        }
        return answer;
    }

    private boolean check(int[] pattern, String word) {
        int[] wPattern = new int[26];
        for (char c : word.toCharArray()) {
            if (Character.isAlphabetic(c)) {
                wPattern[Character.toLowerCase(c) - 'a']++;
            }
        }
        for (int i = 0; i < 26; i++) {
            if (wPattern[i] < pattern[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.shortestCompletingWord("1s3 PSt", new String[]{"step", "steps", "stripe", "stepple"}));
    }
}
