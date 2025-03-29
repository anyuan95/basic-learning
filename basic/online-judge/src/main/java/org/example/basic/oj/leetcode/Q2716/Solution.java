package org.example.basic.oj.leetcode.Q2716;

import java.util.HashSet;
import java.util.Set;

class Solution {
    public int minimizedStringLength(String s) {
        // return s.replaceAll("(.)\\1+", "$1").length();
        Set<Character> chars = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            chars.add(s.charAt(i));
        }
        return chars.size();
    }
}