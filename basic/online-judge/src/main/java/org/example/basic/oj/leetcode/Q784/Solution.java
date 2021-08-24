package org.example.basic.oj.leetcode.Q784;

import java.util.ArrayList;
import java.util.List;

/**
 * @author anyuan
 * @since 2021-08-24 17:48
 */
class Solution {
    public List<String> letterCasePermutation(String s) {
        final char[] chars = s.toCharArray();
        List<String> answer = new ArrayList<>();
        process(answer, chars, 0);
        return answer;
    }

    private void process(List<String> answer, char[] chars, int currentIndex) {
        if (currentIndex == chars.length) {
            answer.add(new String(chars));
            return;
        }
        // 如果当前字符不是字母，则直接处理下一个
        if (!Character.isLetter(chars[currentIndex])) {
            process(answer, chars, currentIndex + 1);
        } else {
            // 这里要注意，你并不知道初始值是小写还是大写，所以必须都考虑。而不能先将他转成小写然后再改回大写（or vice-versa）
            chars[currentIndex] = Character.toUpperCase(chars[currentIndex]);
            process(answer, chars, currentIndex + 1);
            chars[currentIndex] = Character.toLowerCase(chars[currentIndex]);
            process(answer, chars, currentIndex + 1);
        }
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.letterCasePermutation("a1b2"));
        System.out.println(solution.letterCasePermutation("3z4"));
        System.out.println(solution.letterCasePermutation("12345"));
        System.out.println(solution.letterCasePermutation("C"));
    }

}
