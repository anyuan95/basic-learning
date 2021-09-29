package org.example.basic.oj.leetcode.O46;

/**
 * @author anyuan
 * @since 2021-09-27 10:30
 */
class Solution {
    public int translateNum(int num) {
        String str = " " + num;
        final int n = str.length() - 1;
        final char[] chars = str.toCharArray();

        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            int a = chars[i] - '0', b = (chars[i - 1] - '0') * 10 + a;
            if (a >= 0 && a <= 9) {
                dp[i] = dp[i - 1];
            }
            if (b >= 10 && b <= 25) {
                dp[i] += dp[i - 2];
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.translateNum(12258));
    }
}
