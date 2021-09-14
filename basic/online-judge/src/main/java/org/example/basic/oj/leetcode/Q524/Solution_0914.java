package org.example.basic.oj.leetcode.Q524;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

/**
 * @author anyuan
 * @since 2021-09-14 17:31
 */
class Solution_0914 {
    public String findLongestWord(String s, List<String> dictionary) {
        // 思路1：将整个dict排序，仅当后面的匹配字符串长度比当前的长时才更新
//        dictionary.sort((o1, o2) -> (o1 + o2).compareTo(o2 + o1));

        // 思路2：在思路1基础上优化，不再对整个dict排序，而是当发现了更长的匹配串时才进行字典序对比
        final BiFunction<Integer, Integer, Integer> dictOrderBigger = (Integer o1, Integer o2) ->
                (dictionary.get(o1) + dictionary.get(o2)).compareTo(dictionary.get(o2) + dictionary.get(o1)) > 0 ? o2 : o1;

        int longestMatchLength = 0, longestMatchStringIndex = -1;
        for (int i = 0; i < dictionary.size(); i++) {
            final String sub = dictionary.get(i);
            if (isSubSequence(s, sub)) {
                if (longestMatchLength < sub.length()) {
                    longestMatchLength = sub.length();
                    longestMatchStringIndex = i;
                } else if (longestMatchLength == sub.length()) {
                    longestMatchStringIndex = dictOrderBigger.apply(longestMatchStringIndex, i);
                }
            }
        }
        return longestMatchStringIndex == -1 ? "" : dictionary.get(longestMatchStringIndex);
    }

    private boolean isSubSequence(String main, String sub) {
        final char[] mainChars = main.toCharArray();
        final char[] subChars = sub.toCharArray();
        int subCharIndex = 0;
        for (char mainChar : mainChars) {
            if (subCharIndex == subChars.length) {
                return true;
            }
            if (mainChar == subChars[subCharIndex]) {
                subCharIndex++;
            }
        }
        return subCharIndex == subChars.length;
    }

    public static void main(String[] args) {
        final Solution_0914 solution = new Solution_0914();
        System.out.println(solution.isSubSequence("abpcplea", "apple"));
        System.out.println(solution.findLongestWord("abpcplea", Arrays.asList("ale", "apple", "monkey", "plea")));
    }
}
