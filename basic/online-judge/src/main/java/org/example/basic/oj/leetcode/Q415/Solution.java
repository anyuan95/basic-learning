package org.example.basic.oj.leetcode.Q415;

/**
 * @author anyuan
 * @since 2021-09-15 21:54
 */
class Solution {
    public String addStrings(String num1, String num2) {
        int len1 = num1.length(), len2 = num2.length();
        boolean is1Longer = len1 > len2;
        char[] longerChars = is1Longer ? num1.toCharArray() : num2.toCharArray();
        char[] shorterChars = is1Longer ? num2.toCharArray() : num1.toCharArray();
        int longerLen = is1Longer ? len1 : len2, shorterLen = is1Longer ? len2 : len1;
        char[] answerDigits = new char[longerLen + 1];
        boolean add1 = false;
        for (int longerIndex = longerLen - 1, shorterIndex = shorterLen - 1; longerIndex >= 0; longerIndex--, shorterIndex--) {
            int temp;
            if (shorterIndex < 0) {
                temp = longerChars[longerIndex] - '0';
            } else {
                temp = longerChars[longerIndex] - '0' + shorterChars[shorterIndex] - '0';
            }
            if (add1) {
                temp++;
            }
            add1 = temp > 9;
            if (temp > 9) {
                temp -= 10;
            }
            answerDigits[longerIndex + 1] = (char) ('0' + temp);
        }
        // 最后要处理第一位有1的情况
        if (add1) {
            answerDigits[0] = '1';
        }

        // 抹零
        int firstNonZeroIndex = 0;
        while (answerDigits[firstNonZeroIndex] == 0) {
            firstNonZeroIndex++;
        }
        return new String(answerDigits, firstNonZeroIndex, answerDigits.length - firstNonZeroIndex);
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
//        System.out.println(solution.addStrings("456", "77"));
//        System.out.println(solution.addStrings("0", "0"));
        System.out.println(solution.addStrings("1", "9"));
    }
}
