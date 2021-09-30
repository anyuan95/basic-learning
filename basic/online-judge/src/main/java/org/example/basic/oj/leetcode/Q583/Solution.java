package org.example.basic.oj.leetcode.Q583;

/**
 * 删除字符，使得两个字符串相等。求删除字符的最少数量
 *
 * @author anyuan
 * @since 2021-09-25 22:44
 */
class Solution {
    /**
     * dp定义真的是要人老命
     *
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {
        // 定义dp数组，dp[i][j]表示word1的前n位和word2的前n位相同所需删除的最少字符数量
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];

        // 对于word1的前i个字符，如果要和长度为0的word2相等，则必须删除所有字符（即i个），即有dp[i][0]=i
        for (int x = 0; x <= word1.length(); x++) {
            dp[x][0] = x;
        }
        // 对于word2同理
        for (int y = 0; y <= word2.length(); y++) {
            dp[0][y] = y;
        }

        // 对于dp[i][j]，有以下几种情况：
        // 1.word1[i-1]==word2[j-1]，则dp[i][j]==dp[i-1][j-1]
        // 2.word1[i-1]!=word2[j-1]，则再分为以下三种情况：
        // 2.1 删除word1[i-1]，则最少步数就是dp[i-1][j]+1
        // 2.2 删除word2[j-1]，则最少步数就是dp[i][j-1]+1
        // 2.3 删除word1[i-1]和word2[j-1]，则最少步数就是dp[i-1][j-1]+2

        for (int x = 1; x <= word1.length(); x++) {
            for (int y = 1; y <= word2.length(); y++) {
                if (word1.charAt(x-1) == word2.charAt(y-1)) {
                    dp[x][y] = dp[x - 1][y - 1];
                } else {
                    dp[x][y] = Math.min(dp[x - 1][y - 1] + 2, Math.max(dp[x - 1][y], dp[x][y - 1]) + 1);
                }
            }
        }
        return dp[word1.length()][word2.length()];
    }
}
