package org.example.basic.oj.leetcode.Q1417;

/**
 * 虽然AC了，但是写的代码和屎一样
 * 
 * @author anyuan
 * @date 2022-08-11 19:39
 */
class Solution {
    public String reformat(String s) {
        final char[] chars = s.toCharArray();
        int alphaCount = 0;
        int numberCount = 0;
        for (char aChar : chars) {
            if (aChar >= '0' && aChar <= '9') {
                numberCount++;
            } else if (aChar >= 'a' && aChar <= 'z') {
                alphaCount++;
            }
        }
        if (Math.abs(numberCount - alphaCount) > 1) {
            return "";
        }

        // 取两者中较大的一个
        char[] answer = new char[chars.length];
        if (numberCount >= alphaCount) {
            int i = 0;
            for (char aChar : chars) {
                if (aChar >= '0' && aChar <= '9') {
                    answer[i] = aChar;
                    i += 2;
                }
            }
            i = 1;
            for (char aChar : chars) {
                if (aChar >= 'a' && aChar <= 'z') {
                    answer[i] = aChar;
                    i += 2;
                }
            }
        } else if (numberCount < alphaCount) {
            int i = 0;
            for (char aChar : chars) {
                if (aChar >= 'a' && aChar <= 'z') {
                    answer[i] = aChar;
                    i += 2;
                }
            }
            i = 1;
            for (char aChar : chars) {
                if (aChar >= '0' && aChar <= '9') {
                    answer[i] = aChar;
                    i += 2;
                }
            }
        }
        return new String(answer);
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.reformat("covid2019"));
    }
}
