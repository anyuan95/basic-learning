package org.example.basic.oj.leetcode.Q482;

import java.util.ArrayList;
import java.util.List;

/**
 * @author anyuan
 * @since 2021-10-04 19:00
 */
class Solution {

    private final static int ALPHABET_OFFSET = 'A' - 'a';
    /**
     * 第一个分组包含的字符个数必须小于等于 K
     *
     * @param s
     * @param k
     * @return
     */
    public String licenseKeyFormatting(String s, int k) {
        final char[] chars = new char[s.length()];
        int charsIndex = 0;
        char temp;
        for (int i = 0; i < s.length(); i++) {
            temp = s.charAt(i);
            if (temp >= 'a' && temp <= 'z') {
                chars[charsIndex++] = (char) (temp + ALPHABET_OFFSET);
            } else if (temp != '-') {
                chars[charsIndex++] = temp;
            }
        }
        int nonDashCharCount = charsIndex;
        if (nonDashCharCount <= k) {
            // 如果非破折号字符数量小于等于一段的数量，那直接返回就是了
            return new String(chars, 0, nonDashCharCount);
        }
        final StringBuilder builder = new StringBuilder();
        // 两种情况，一种是每一段都一样长，另一种是第一段短于k
        // 如果正好能分完，那从第一段开始后边就是n/k-1段，否则后边就是n/k段
        int partCountFromSecond = nonDashCharCount % k == 0 ? nonDashCharCount / k - 1 : nonDashCharCount / k;
        // 计算第一段的长度
        int firstPartLen = nonDashCharCount - partCountFromSecond * k;
        // 拼第一部分
        builder.append(chars, 0, firstPartLen);
        // 拼剩余部分
        for (int i = 0, offset = firstPartLen; i < partCountFromSecond; i++, offset += k) {
            // 当前段的位置是从 firstPartLen + i * k，长度就是k
            builder.append('-').append(chars, offset, k);
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.licenseKeyFormatting("5F3Z-2e-9-w", 4));
        System.out.println(solution.licenseKeyFormatting("5F3Z-2e-9-w", 1));
        System.out.println(solution.licenseKeyFormatting("2-5g-3-J", 2));
        System.out.println(solution.licenseKeyFormatting("2-5g-3-J", 10));
    }
}
