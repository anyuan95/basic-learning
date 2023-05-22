package org.example.basic.oj.leetcode.Q1475;

class Solution {
    public int[] finalPrices(int[] prices) {
        final int n = prices.length;
        final int[] answer = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            int discount = 0;
            for (int j = i + 1; j < n; j++) {
                if (prices[j] <= prices[i]) {
                    discount = prices[j];
                    break;
                }
            }
            answer[i] = prices[i] - discount;
        }
        return answer;
    }
}
