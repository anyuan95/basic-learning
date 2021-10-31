package org.example.basic.oj.leetcode.Q500;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author anyuan
 * @since 2021-10-31 23:06
 */
class Solution {
    private static final int[] keyboard = new int[]{2, 3, 3, 2, 1, 2, 2, 2, 1, 2, 2, 2, 3, 3, 1, 1, 1, 1, 2, 1, 1, 3, 1, 3, 1, 3};

    /**
     * 第一行由字符 "qwertyuiop" 组成。
     * 第二行由字符 "asdfghjkl" 组成。
     * 第三行由字符 "zxcvbnm" 组成。
     *
     * @param words
     * @return
     */
    public String[] findWords(String[] words) {
        final List<String> answer = new ArrayList<>();
        seeWord:
        for (String word : words) {
            int numbersFrom = keyboard[Character.toLowerCase(word.charAt(0)) - 'a'];
            for (int i = 1; i < word.length(); i++) {
                if (keyboard[Character.toLowerCase(word.charAt(i)) - 'a'] != numbersFrom) {
                    continue seeWord;
                }
            }
            answer.add(word);
        }
        return answer.toArray(new String[]{});
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.findWords(new String[]{"Hello", "Alaska", "Dad", "Peace"})));
    }

}
