package org.example.basic.oj.leetcode.Q524;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.function.BiFunction;

/**
 * 给定字典数组，给定字符串s
 * 问：在字典数组中找出，在可以由s删除部分字符获得的词中，长度最长且字典序最小的
 *
 * @author anyuan
 * @since 2021-08-18 17:54
 */
class Solution {
    public String findLongestWord(String s, List<String> dictionary) {
        int maxLength = 0;
        String longestWord = "";

        final BiFunction<String, String, String> dictOrderBigger = (String o1, String o2) ->
                (o1 + o2).compareTo(o2 + o1) > 0 ? o2 : o1;

        for (String word : dictionary) {
            if (isSubSequence(s, word)) {
                if (word.length() > maxLength) {
                    maxLength = word.length();
                    longestWord = word;
                } else if (word.length() == maxLength) {
                    longestWord = dictOrderBigger.apply(longestWord, word);
                }
            }
        }
        return longestWord;
    }

    private boolean isSubSequence(String main, String pattern) {
        final char[] mainChars = main.toCharArray();
        final char[] patternChars = pattern.toCharArray();
        int patternIndex = 0;
        for (char mainChar : mainChars) {
            if (patternIndex == patternChars.length) {
                return true;
            }
            if (mainChar == patternChars[patternIndex]) {
                patternIndex++;
            }
        }
        return patternIndex == patternChars.length;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
//        System.out.println(solution.isSubSequence("abpcplea", "apple"));
//        System.out.println(solution.isSubSequence("apple", "apple"));
        System.out.println(solution.findLongestWord("abce", Lists.newArrayList("abe", "abc")));
    }


}
