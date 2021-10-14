package org.example.basic.oj.leetcode.Q6;

/**
 * @author anyuan
 * @date 2021-10-14 10:58
 */
class Solution {

    /**
     * 巨慢的纯模拟
     *
     * 其实整理一下结果中每个位置的字符在原字符串中的下标位置，理论上是可以On解决的
     *
     * @param s
     * @param numRows
     * @return
     */
    public String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        final char[][] chars = convertToChars(s, numRows);
        final StringBuilder builder = new StringBuilder();
        for (char[] aChar : chars) {
            for (char aaChar : aChar) {
                if (aaChar != 0) {
                    builder.append(aaChar);
                }
            }
        }
        return builder.toString();
    }

    public char[][] convertToChars(String s, int numRows) {
        final int n = s.length();
        // n个字符按照numRow拆分，拆分后得到的数组高度一定是nr
        // 宽度的计算：按照nr拆分，那么每2nr-2个字符可以作为一组，每一组的宽度是nr-1。
        // 则总宽度为 n / (2nr-2) * (nr-1) +
        // 剩下的部分需要分开考虑：k = n % (2nr-2)
        // 若k<=nr，那就加1
        // 否则就加1+(k-nr)

        // 每一组的字符个数
        int eachGroupCharCount = 2 * numRows - 2;
        // 每一组拆分后的宽度
        int eachGroupWidth = numRows - 1;
        int realWidth = n / eachGroupCharCount * eachGroupWidth
                + (n % eachGroupCharCount <= numRows ? 1 : 1 + n % eachGroupCharCount - numRows);
        char[][] chars = new char[numRows][realWidth];

        // 直接按组处理
        final int groupCount = (int) Math.ceil(n * 1.0 / eachGroupCharCount);
        for (int groupNo = 0, charIndex = 0; groupNo < groupCount; groupNo++) {
            int x = groupNo * eachGroupWidth;
            // 考虑提前结束的情况，即剩余的部分不够了的情况

            // 先拼装竖着的一排
            for (int j = 0; j < numRows; j++) {
                if (charIndex == n) {
                    return chars;
                }
                chars[j][x] = s.charAt(charIndex++);
            }
            // 然后开始拼斜着的部分
            for (int j = 1; j <= numRows - 2; j++) {
                if (charIndex == n) {
                    return chars;
                }
                chars[numRows - 1 - j][x + j] = s.charAt(charIndex++);
            }
        }
        return chars;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.convert("PAYPALISHIRING", 3));
        System.out.println(solution.convert("PAYPALISHIRING", 4));
    }
}
