package org.example.basic.oj.leetcode.Q91;

/**
 * @author anyuan
 * @since 2021-09-14 23:46
 */
class Solution {
    public int numDecodings(String s) {
        final int n = s.length();
        int[] dp = new int[n + 1];
        s = ' ' + s;
        final char[] chars = s.toCharArray();

        // 状态转移分析：
        // 一种情况是，只能由当前位置单独作为一个数，假设这个数为a。这种情况下必须满足a∈[1,9]，转移方程为dp[i]=dp[i-1]
        // 一种情况是，只能由当前位置和前一个位置一起作为一个数，假设这个数为b。这种情况下必须满足b∈[10,26]，转移方程为dp[i]=dp[i-2]
        // 还有一种情况是，既可以是情况1，又可以是情况2。转移方程为dp[i]=dp[i-1]+dp[i-2]

        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            // a:当前位置单独组成一个数
            // b:当前位置和前一个位置一起组成数
            int a = chars[i] - '0', b = (chars[i - 1] - '0') * 10 + a;
            if (a >= 1 && a <= 9) {
                dp[i] = dp[i - 1];
            }
            if (b >= 10 && b <= 26) {
                dp[i] += dp[i - 2];
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println((int)' ');
        final Solution solution = new Solution();
        System.out.println(solution.numDecodings("226"));
        System.out.println(solution.numDecodings("12"));
        System.out.println(solution.numDecodings("06"));
        System.out.println(solution.numDecodings("0"));
        System.out.println(solution.numDecodings("14629"));
        System.out.println(solution.numDecodings("111415"));
        System.out.println(solution.numDecodings("1123"));
    }
}
