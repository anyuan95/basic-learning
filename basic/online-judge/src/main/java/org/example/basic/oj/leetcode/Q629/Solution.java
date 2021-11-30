package org.example.basic.oj.leetcode.Q629;

/**
 * @author anyuan
 * @since 2021-11-11 23:23
 */
class Solution {
    public static final int MOD = (int) 1e9 + 7;

    /**
     * 思路：做一个二维数组，i是下标（或者说是当前值），j是逆序对个数，值是一共有i个数时，含有j个逆序对的排列个数
     * <p>
     * 由于是升序往里放i的，所以新放进来的i一定是大于原来数组中的所有元素的
     * 就是说，向不同的位置插入i之后，得到的新数组的逆序对数量实际就是原来的数组基础上加i-k
     * <p>
     * <p>
     * 这个解法的时间复杂度是O(n^2*k)，题目给的n和k最大值都是1000，最终的时间量级是10^9，会超时
     *
     * @param n
     * @param k
     * @return
     */
    public int kInversePairs(int n, int k) {
        long[][] dp = new long[n + 1][k + 1];
        // 取0个值的时候，组成的逆序对一定全都是0
        dp[1][0] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j <= k; j++) {
                // 填写共i个数时，含有j个逆序对的排列数量
                // 此处应该是[0,i-1]，因为原始的列表里只有i-1个元素，实际上能放的位置只有[0,i-1]
                for (int m = 0; m < i; m++) {
                    // 如果放在m位置，那么就会多出i-1-m个逆序对，所以仅当原本有j-(i-1-m)个逆序对的排列，加上i，才能构成j个逆序对
                    // 前提是合理
                    if (j - (i - 1 - m) >= 0) {
                        dp[i][j] = (dp[i][j] + dp[i - 1][j - (i - 1 - m)]) % MOD;
                    }
                }
            }
        }
        return (int) dp[n][k];
    }

    /**
     * 使用前缀和的方式，减少每个状态的计算复杂度
     *
     * @param n
     * @param k
     * @return
     */
    public int kInversePairs_better(int n, int k) {
        long[][] dp = new long[n + 1][k + 1];
        // 取0个值的时候，组成的逆序对一定全都是0
        dp[1][0] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i][0] = 1;
            for (int j = 1; j <= k; j++) {
                dp[i][j] = (dp[i - 1][j] + dp[i][j - 1]) % MOD;
                // 填写共i个数时，含有j个逆序对的排列数量
                // 此处应该是[0,i-1]，因为原始的列表里只有i-1个元素，实际上能放的位置只有[0,i-1]
                if (j - (i - 1) > 0) {
                    dp[i][j] = (dp[i][j] + MOD - dp[i - 1][j - i]) % MOD;
                }
            }
        }
        return (int) dp[n][k];
    }


    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.kInversePairs(3, 1));
        System.out.println(solution.kInversePairs(1000, 1000));

        System.out.println(solution.kInversePairs_better(3, 1));
        System.out.println(solution.kInversePairs_better(1000, 1000));
    }
}
