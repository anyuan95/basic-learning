package org.example.basic.oj.leetcode.Q639;

/**
 * @author anyuan
 * @since 2021-09-27 11:37
 */
class Solution {

    public static final int MOD = (int) 1e9 + 7;

    /**
     * 在Q91的基础上，加了可以表示任意数字的*
     * A-Z用1-26表示
     *
     *
     * WA记录：
     * 1.第一次忘记处理*x的情况了
     * 2.第二次边界条件弄错了：固定值判断时，单独解析需要在[1,9]中，而不是[0,9]
     * 3.第三次计算出现溢出问题了，把数组改成long，最后返回时再使用int
     *
     * @param s
     * @return
     */
    public int numDecodings(String s) {
        /**
         * 考虑*的所有可能性：
         * 自由的*可以表示1-9
         * 1.如果要和前一个合并：
         * - 如果前一个是1，则这个可以是0-9
         * - 如果前一个是2，则这个可以使0-6
         * - 如果前一个是0,[3,8]，则这个值只能尝试和后面合并
         * 2.如果要和后一个合并：
         * - 如果后一个是0-6，则这个可以是1或2
         * - 如果后一个是7-9，则这个可以是1
         * 3.如果不和前后合并：
         * - 则这个可以是1-9
         */
        if (s.charAt(0) == '0') {
            return 0;
        }
        final int n = s.length();
        s = ' ' + s;
        final char[] chars = s.toCharArray();
        long[] dp = new long[n + 1];

        dp[0] = 1;

        for (int i = 1; i <= n; i++) {
            // 上一个
            char lastChar = chars[i - 1];
            // 这一个
            char thisChar = chars[i];
            // 首先，无论上一个是什么，这个位置如果是*，就都可以单独解析

            // 如果上一个是1，则这个可以拼一起，则这个可以是1-9
            // 如果上一个是2，则这个只能是1-6
            // 如果上一个是3-9，则这个只能单独解析
            if (thisChar == '*') {
                // 单独解析：9n种  xxxxxa
                dp[i] += dp[i - 1] * 9;
                // 拼装解析
                if (lastChar == '*') {
                    // 上一位是*：两个连续的*拼装的数，只可能是11-19,21-25
                    dp[i] += dp[i - 2] * (9 + 6);
                } else if (lastChar == '1') {
                    // 上一位是1：9n种  xxxx1b
                    dp[i] += dp[i - 2] * 9;
                } else if (lastChar == '2') {
                    // 上一位是2：6n种  xxxx2b
                    dp[i] += dp[i - 2] * 6;
                } else if (lastChar - '0' >= 3 && lastChar - '0' <= 9) {
                    // 上一位是3-9：0种  拼不了
                    //do nothing
                }
            } else {
                // 注意处理*x的情况

                // 否则跟之前的处理方式一样，计算一下连续两位的值
                // 如果这一位不是*，那就正常进行解析即可
                int a = thisChar - '0', b = (lastChar - '0') * 10 + a;
                // 单独解析
                if (a >= 1 && a <= 9) {
                    dp[i] = dp[i - 1];
                }
                // 两个数一起解析
                if (lastChar == '*') {
                    // 如果上一个是*，那就要取决于当前是0-6还是其他了
                    if (a >= 0 && a <= 6) {
                        // 如果是0-6，则前面的*可以表示1或2，即组合后可以表示1x或2x
                        dp[i] += dp[i - 2] * 2;
                    } else { // 7-9
                        // 如果是7-9，则前面的*只能表示1，即组合后表示1x
                        dp[i] += dp[i - 2];
                    }
                } else if (b >= 10 && b <= 26) {
                    dp[i] += dp[i - 2];
                }
            }
            dp[i] %= MOD;
        }
        return (int) dp[n];
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.numDecodings("*1*1*0"));
        System.out.println(solution.numDecodings("*1*1*0*"));
        System.out.println(solution.numDecodings("*1*1*0*0"));
        System.out.println(solution.numDecodings("7*9*3*6*3*0*5*4*9*7*3*7*1*8*3*2*0*0*6*"));
        System.out.println(solution.numDecodings("**"));
        System.out.println(solution.numDecodings("*"));
        System.out.println(solution.numDecodings("1*"));
        System.out.println(solution.numDecodings("2*"));
    }
}
