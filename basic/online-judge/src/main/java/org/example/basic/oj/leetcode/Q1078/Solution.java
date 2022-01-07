package org.example.basic.oj.leetcode.Q1078;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author anyuan
 * @since 2021-12-26 14:06
 */
class Solution {
    public String[] findOcurrences(String text, String first, String second) {
        List<String> occurences = new ArrayList<>();
        final String[] pieces = text.split(" ");
        final int n = pieces.length;
        int i = 0;
        while (i < n - 2) {
            if (first.equals(pieces[i]) && second.equals(pieces[i + 1])) {
                occurences.add(pieces[i + 2]);
            }
            i++;
        }
        return occurences.toArray(new String[0]);
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.findOcurrences("jkypmsxd jkypmsxd kcyxdfnoa jkypmsxd kcyxdfnoa jkypmsxd kcyxdfnoa kcyxdfnoa jkypmsxd kcyxdfnoa",
                "kcyxdfnoa",
                "jkypmsxd")));
    }
}
