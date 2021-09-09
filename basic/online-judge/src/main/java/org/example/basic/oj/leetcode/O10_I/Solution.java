package org.example.basic.oj.leetcode.O10_I;

/**
 * @author anyuan
 * @since 2021-09-04 18:14
 */
class Solution {
    public int fib(int n) {
        int first = 0, second = 1;
        if (n == 0) {
            return first;
        } else if (n == 1) {
            return second;
        }
        for (int i = 2; i <= n; i++) {
            int temp = (first + second) % 1000000007;
            first = second;
            second = temp;
        }
        return second;
    }
}
