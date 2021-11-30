package org.example.basic.oj.leetcode.Q520;

/**
 * @author anyuan
 * @since 2021-11-13 23:26
 */
class Solution {
    public boolean detectCapitalUse(String word) {
        if (word.length() == 1) {
            return true;
        }
        final char[] chars = word.toCharArray();
        if (Character.isUpperCase(chars[0]) ) {
            // 首字母是大写，那么后边就必须都是大写或都是小写
            boolean elseIsUpper = Character.isUpperCase(chars[1]);
            for (int i = 2; i < chars.length; i++) {
                if (Character.isUpperCase(chars[i]) != elseIsUpper) {
                    return false;
                }
            }
            return true;
        } else {
            // 否则首字母是小写，那剩下的就必须都是小写
            for (int i = 1; i < chars.length; i++) {
                if (Character.isUpperCase(chars[i])) {
                    return false;
                }
            }
            return true;
        }
    }
}