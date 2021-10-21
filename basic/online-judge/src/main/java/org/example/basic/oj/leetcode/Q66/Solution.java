package org.example.basic.oj.leetcode.Q66;

import java.util.Arrays;

/**
 * @author anyuan
 * @date 2021-10-21 11:32
 */
class Solution {
    public int[] plusOne(int[] digits) {
        final int n = digits.length;
        // 当前位需要进位的值（实际上只会有0和1）
        digits[n - 1] += 1;
        int currentDigitCarry = digits[n - 1] / 10;
        digits[n - 1] %= 10;
        for (int i = n - 2; i >= 0 && currentDigitCarry != 0; i--) {
            digits[i] += currentDigitCarry;
            currentDigitCarry = digits[i] / 10;
            digits[i] %= 10;
        }
        if (currentDigitCarry == 1) {
            int[] answer = new int[n + 1];
            answer[0] = 1;
            System.arraycopy(digits, 0, answer, 1, n);
            return answer;
        }
        return digits;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.plusOne(new int[]{4, 3, 2, 1})));
        System.out.println(Arrays.toString(solution.plusOne(new int[]{4, 3, 2, 9})));
        System.out.println(Arrays.toString(solution.plusOne(new int[]{9, 9, 9, 9})));
        System.out.println(Arrays.toString(solution.plusOne(new int[]{9, 0, 9})));
    }
}
