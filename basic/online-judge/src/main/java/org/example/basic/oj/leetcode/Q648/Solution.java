package org.example.basic.oj.leetcode.Q648;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
    public String replaceWords(List<String> dictionary, String sentence) {
        // 预处理词根列表
        dictionary.sort(Comparator.comparing(String::length));
//        Set<String> betterDict = new HashSet<>();
//        for (String dict : dictionary) {
//
//        }
        final String[] words = sentence.split(" ");
        final StringBuilder answer = new StringBuilder();
        for (String word : words) {
            boolean matched = false;
            for (String dict : dictionary) {
                if (word.startsWith(dict)) {
                    matched = true;
                    answer.append(dict).append(' ');
                    break;
                }
            }
            if (!matched) {
                answer.append(word).append(' ');
            }
        }
        answer.deleteCharAt(answer.length() - 1);
        return answer.toString();
    }
}
