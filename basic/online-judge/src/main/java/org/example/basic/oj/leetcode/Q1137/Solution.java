package org.example.basic.oj.leetcode.Q1137;

/**
 * @author anyuan
 * @since 2021-08-08 13:09
 */
class Solution {
    public int tribonacci(int n) {
        int last3Result = 0, last2Result = 1, last1Result = 1;
        if (n == 0) {
            return last3Result;
        } else if (n == 1) {
            return last2Result;
        } else if (n == 2) {
            return last1Result;
        }
        for (int i = 3; i <= n; i++) {
            int currentResult = last3Result + last2Result + last1Result;
            last3Result = last2Result;
            last2Result = last1Result;
            last1Result = currentResult;
        }
        return last1Result;
    }
}
