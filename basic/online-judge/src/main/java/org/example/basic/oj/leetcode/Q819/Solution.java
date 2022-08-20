package org.example.basic.oj.leetcode.Q819;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

class Solution {
    public String mostCommonWord(String paragraph, String[] banned) {
        Map<String, Integer> map = new HashMap<>();
        paragraph = paragraph.toLowerCase(Locale.ROOT);
        paragraph = paragraph.replace('!',' ')
                .replace('?',' ')
                .replace('\'',' ')
                .replace(',',' ')
                .replace(';',' ')
                .replace('.',' ')
                .trim();
        final String[] words = paragraph.split(" ");
        for (String word : words) {
            if (word.length() > 0) {
                map.put(word, map.getOrDefault(word, 0) + 1);
            }
        }
        for (String s : banned) {
            map.remove(s);
        }
        int max = 0;
        String answer = null;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                answer = entry.getKey();
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.mostCommonWord("Bob. hIt, baLl", new String[]{"bob", "hit"}));
    }
}
