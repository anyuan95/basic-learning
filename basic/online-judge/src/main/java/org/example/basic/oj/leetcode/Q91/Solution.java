package org.example.basic.oj.leetcode.Q91;

/**
 * @author anyuan
 * @since 2021-09-14 23:46
 */
class Solution {
    public int numDecodings(String s) {
        // 一定是每次截取1或2个字符，如果截取2个字符，则需要判断是否合法
        final int n = s.length();
        int[] dp = new int[n];
        // 换一种思路：每个字符可能跟前面一起，可能跟后面一起，可能自己

        return 0;
    }
}
