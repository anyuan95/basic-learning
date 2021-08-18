package org.example.basic.oj.leetcode.Q322;

import java.util.Arrays;

/**
 * 零钱兑换
 * <p>
 * 给定硬币类型数组coins，给定目标总金额amount，
 * 每种硬币都有无限个。
 * 求凑成总金额的最小个数。
 * <p>
 * 其实也算是一种背包问题？
 *
 * @author anyuan
 * @since 2021-08-18 10:54
 */
class Solution {

    /**
     * 思路：
     * 求每个剩余钱数rest对应的最少硬币数
     *
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange1(int[] coins, int amount) {
        // 调整成升序，减少判断
        Arrays.sort(coins);
        int[] dp = new int[amount + 1];

        return process(coins, amount, dp);
    }

    /**
     * 求最少多少个硬币能组成rest
     *
     * @param coins
     * @param rest
     * @param dp
     * @return
     */
    private int process(int[] coins, int rest, int[] dp) {
        if (rest == 0) {
            return 0;
        } else if (rest < 0) {
            // 减多了
            return -1;
        } else if (rest < coins[0]) {
            // 最小的硬币也比rest大
            return -1;
        }
        if (dp[rest] != 0) {
            return dp[rest];
        }
        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            if (coin <= rest) {
                int p = process(coins, rest - coin, dp);
                if (p != -1) {
                    min = Math.min(p + 1, min);
                }
            }
        }
        dp[rest] = min == Integer.MAX_VALUE ? -1 : min;
        return dp[rest];
    }

    /**
     * 直接归纳
     *
     * 整理dp[amount+1]数组，下标为rest，即剩余要攒出来的钱数。
     * 对于amount，和coins数组中小于amount的部分[c1,c2,c3...cn]，有：
     * dp[rest] = min( dp[rest - c1], dp[rest - c2], dp[rest - c3], ... dp[rest - cn])
     *
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange2(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int rest = 1; rest <= amount; rest++) {
            for (int coin : coins) {
                if (rest >= coin && dp[rest - coin] != Integer.MAX_VALUE) {
                    dp[rest] = Math.min(dp[rest], dp[rest - coin] + 1);
                }
            }
        }
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.coinChange2(new int[]{1, 2, 5}, 11));
        System.out.println(solution.coinChange2(new int[]{1}, 0));
        System.out.println(solution.coinChange2(new int[]{1}, 1));
        System.out.println(solution.coinChange2(new int[]{1}, 2));
        System.out.println(solution.coinChange2(new int[]{2}, 3));
    }

}
