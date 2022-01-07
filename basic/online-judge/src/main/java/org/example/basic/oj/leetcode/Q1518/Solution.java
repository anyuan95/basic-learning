package org.example.basic.oj.leetcode.Q1518;

/**
 * @author anyuan
 * @since 2021-12-17 00:06
 */
class Solution {
    public int numWaterBottles(int numBottles, int numExchange) {
        int emptyBottles = numBottles;
        while (emptyBottles >= numExchange) {
            numBottles += emptyBottles / numExchange;
            emptyBottles = emptyBottles % numExchange + emptyBottles / numExchange;
        }
        return numBottles;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.numWaterBottles(9, 3));
    }
}
