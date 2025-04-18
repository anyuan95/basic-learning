package org.example.basic.oj.leetcode.Q1455;

class Solution {
    public int isPrefixOfWord(String sentence, String searchWord) {
        final String[] words = sentence.split(" ");
        for (int i = 0; i < words.length; i++) {
            if (words[i].startsWith(searchWord)) {
                return i+1;
            }
        }
        return -1;
    }
}
