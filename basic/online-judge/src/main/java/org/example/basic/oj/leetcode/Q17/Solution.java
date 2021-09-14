package org.example.basic.oj.leetcode.Q17;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author anyuan
 * @since 2021-09-14 18:19
 */
class Solution {
    /**
     * 0 <= digits.length <= 4
     * digits[i] 是范围 ['2', '9'] 的一个数字。
     *
     * @param digits
     * @return
     */
    public List<String> letterCombinations(String digits) {
        if (digits.length() == 0) {
            return Collections.emptyList();
        }
        char[][] letters = new char[][]{
                {'a', 'b', 'c'},
                {'d', 'e', 'f'},
                {'g', 'h', 'i'},
                {'j', 'k', 'l'},
                {'m', 'n', 'o'},
                {'p', 'q', 'r', 's'},
                {'t', 'u', 'v'},
                {'w', 'x', 'y', 'z'}
        };
        List<String> answer = new ArrayList<>();
        final char[] nums = digits.toCharArray();
        process(answer, new char[nums.length], letters, nums, 0);
        return answer;
    }

    private void process(List<String> answer, char[] currentAnswer, char[][] letters, char[] nums, int currentIndex) {
        if (currentIndex == nums.length) {
            answer.add(new String(currentAnswer));
            return;
        }
        // 2对应letters[0]
        // 0的ASCII是48
        for (char availableLetter : letters[nums[currentIndex] - 50]) {
            currentAnswer[currentIndex] = availableLetter;
            process(answer, currentAnswer, letters, nums, currentIndex + 1);
        }
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.letterCombinations("23"));
        System.out.println(solution.letterCombinations(""));
    }

}
