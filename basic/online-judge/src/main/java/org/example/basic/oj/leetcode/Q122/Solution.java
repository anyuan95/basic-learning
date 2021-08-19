package org.example.basic.oj.leetcode.Q122;

/**
 * @author anyuan
 * @since 2021-08-19 10:15
 */
class Solution {

    /**
     * 将整个数组构成一个下标-值的折线图，则最大的收益就是所有斜率为正的部分的收益和。
     * 相当于在所有低点i买，在所有高点j（j>i）卖
     *
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int answer = 0;
        for (int i = 1; i < prices.length; i++) {
            answer += Math.max(prices[i] - prices[i - 1], 0);
        }
        return answer;
    }
}
