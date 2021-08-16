package org.example.basic.oj.leetcode.Q557;

import cn.hutool.core.util.StrUtil;

import java.util.Comparator;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author anyuan
 * @since 2021-08-15 12:13
 */
class Solution {

    public String reverseWords(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        int wordStart = 0;
        boolean findWord = false;
        final char[] chars = s.toCharArray();
        for (int current = 0; current < chars.length; current++) {
            if (!findWord && chars[current] != ' ') {
                findWord = true;
                wordStart = current;
            } else if (findWord && chars[current] == ' ') {
                reversePart(chars, wordStart, current - 1);
                findWord = false;
            }
        }
        if (findWord) {
            reversePart(chars, wordStart, chars.length - 1);
        }
        return new String(chars);
    }


    public void reversePart(char[] s, int left, int right) {
        if (s == null || s.length == 0) return;
        while (left <= right) {
            swap(s, left++, right--);
        }
    }

    private void swap(char[] array, int index1, int index2) {
        if (index1 == index2) return;
        char temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        final String s = "Let's take LeetCode contest";
        Function<String, String> reverseWords = str ->
                StrUtil.split(str, ' ').stream().map(StrUtil::reverse).collect(Collectors.joining(" "));
        System.out.println(reverseWords.apply(s));
        System.out.println(solution.reverseWords(s));

    }
}
