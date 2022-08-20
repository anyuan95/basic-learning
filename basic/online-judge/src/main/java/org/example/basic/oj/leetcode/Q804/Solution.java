package org.example.basic.oj.leetcode.Q804;

import java.util.HashSet;
import java.util.Set;

class Solution {

    public static final String[] DICT = new String[]{".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};

    public int uniqueMorseRepresentations(String[] words) {
        Set<String> reps = new HashSet<>();
        for (String word : words) {
            reps.add(translateMorse(word));
        }
        return reps.size();
    }

    private String translateMorse(String word) {
        final StringBuilder stringBuilder = new StringBuilder();
        for (char c : word.toCharArray()) {
            stringBuilder.append(DICT[c - 'a']);
        }
        return stringBuilder.toString();
    }
}
