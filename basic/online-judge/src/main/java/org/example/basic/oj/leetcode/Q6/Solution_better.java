package org.example.basic.oj.leetcode.Q6;

/**
 * @author anyuan
 * @date 2021-10-14 11:51
 */
class Solution_better {
    public String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        final StringBuilder builder = new StringBuilder();
        // 考虑每一行要输入的字符的位置
        final int n = s.length();
        final int eachGroupCharCount = 2 * numRows - 2;
        for (int rowNum = 0; rowNum < numRows; rowNum++) {
            // charIndex是当前组左上角字符的位置
            for (int charIndex = 0; charIndex < n; charIndex += eachGroupCharCount) {
                if (charIndex + rowNum < n) {
                    builder.append(s.charAt(charIndex + rowNum));
                }
                // 在每两段中间插入一个斜边上的字符
                // 一定至多只会有一个字符
                // 从下一个起始位置开始倒着找

                // 注意第0行和第n-1行是没有中间字符的
                if (rowNum > 0 && rowNum < numRows - 1) {
                    int anotherIndex = charIndex + eachGroupCharCount - rowNum;
                    if (anotherIndex < n) {
                        builder.append(s.charAt(anotherIndex));
                    }
                }
            }
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        final Solution_better solution = new Solution_better();
        // PAHNAPLSIIGYIR
        // PAHNAPLSIIGYIR
        System.out.println(solution.convert("PAYPALISHIRING", 3));
        // PINALSIGYAHRPI
        // PINALSIGYAHRPI
        System.out.println(solution.convert("PAYPALISHIRING", 4));
    }
}
