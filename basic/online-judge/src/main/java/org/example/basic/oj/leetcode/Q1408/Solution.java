package org.example.basic.oj.leetcode.Q1408;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author anyuan
 * @date 2022-08-30 22:40
 */
class Solution {
    public List<String> stringMatching(String[] words) {
        Arrays.sort(words, Comparator.comparing(String::length).thenComparing(Comparator.naturalOrder()));
        final List<String> answer = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            for (int j = i+1; j < words.length; j++) {
                if (words[j].contains(words[i])) {
                    answer.add(words[i]);
                    break;
                }
            }
        }
        return answer;
    }
}
