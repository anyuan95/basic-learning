package org.example.basic.oj.leetcode.Q121;

/**
 * @author anyuan
 * @since 2021-08-19 10:11
 */
public class Solution_Simple {

    public int maxProfit(int[] prices) {
        int min = prices[0], max = 0;
        for (int i = 1; i < prices.length; i++) {
            max = Math.max(prices[i] - min, max);
            min = Math.min(prices[i],min);
        }
        return max;
    }
}
