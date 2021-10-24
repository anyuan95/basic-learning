package org.example.basic.oj.leetcode.Q273;

import java.util.Arrays;

/**
 * @author anyuan
 * @since 2021-10-11 23:34
 */
class Solution {
    public static final String[] units = {"", "Thousand", "Million", "Billion"};
    public static final String[] numsLessThan20 = {"Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine",
            "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    public static final String[] numsOf10k = {"Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};

    public String numberToWords(int num) {
        if (num == 0) {
            return numsLessThan20[0];
        }
        final StringBuilder sb = new StringBuilder();
        int unitIndex = 0;
        while (num > 0) {
            if (num % 1000 != 0) {
                sb.insert(0, new StringBuilder(num2Str(num % 1000)).append(units[unitIndex]).append(' '));
            }
            unitIndex++;
            num /= 1000;
        }
        while (sb.charAt(sb.length() - 1) == ' ') {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    private String num2Str(int num) {
        StringBuilder sb = new StringBuilder();
        if (num >= 100) {
            sb.append(numsLessThan20[num / 100]).append(" Hundred ");
            num %= 100;
        }
        if (num >= 20) {
            sb.append(numsOf10k[num / 10 - 2]).append(' ');
            num %= 10;
        }
        // 最后也就剩下0-20了
        if (num != 0) {
            sb.append(numsLessThan20[num]).append(' ');
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.numberToWords(123456789));
    }

}
