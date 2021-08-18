package org.example.basic.oj.leetcode.Q97;

/**
 * 交错字符串
 *
 * @author anyuan
 * @since 2021-08-18 17:03
 */
class Solution {

    /**
     * 样本对应模型
     *
     * @param s1
     * @param s2
     * @param s3
     * @return
     */
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1 == null || s2 == null || s3 == null) {
            return false;
        }
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }
        return dp(s1, s2, s3);
    }

    /**
     * str1的前i个字符 + str2的前j个字符，是否能组成str3的前i+j的串
     * 以此构成dp表
     */
    private boolean dp(String s1, String s2, String s3) {
        boolean[][] dp = new boolean[s1.length() + 1][s2.length() + 1];
        dp[0][0] = true;
        final char[] chars1 = s1.toCharArray();
        final char[] chars2 = s2.toCharArray();
        final char[] chars3 = s3.toCharArray();

        // 填充[x][0]
        for (int i = 1; i <= chars1.length; i++) {
            if (chars1[i - 1] != chars3[i - 1]) {
                break;
            }
            dp[i][0] = true;
        }
        // 填充[0][y]
        for (int j = 1; j <= chars2.length; j++) {
            if (chars2[j - 1] != chars3[j - 1]) {
                break;
            }
            dp[0][j] = true;
        }
        // 推导
        // 想要得到dp[i][j]，即 s1的前i + s2的前j 是否能拼成 s3的前i+j
        // 则现在从后往前推（样本对应模型经常会从后往前推导）
        // 如果s3[i+j-1] == s1[i-1]，则要使dp[i][j]=true，还需要dp[i-1][j]=true，即s1的前i-1 + s2的前j 能拼成 s3的前i-1+j
        // 同理，如果s3[i+j-1] == s2[j-1]，则要使dp[i][j]=true，还需要dp[i][j-1]=true，即s2的前j-1 + s1的前i 能拼成 s3的前i+j-1
        // 故可以得到公式
        for (int i = 1; i <= chars1.length; i++) {
            for (int j = 1; j <= chars2.length; j++) {
                dp[i][j] = (chars3[i + j - 1] == chars1[i - 1] && dp[i - 1][j])
                        || (chars3[i + j - 1] == chars2[j - 1] && dp[i][j - 1]);
            }
        }
        return dp[s1.length()][s2.length()];
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.dp("aabcc", "dbbca", "aadbbcbcac"));
    }
}
