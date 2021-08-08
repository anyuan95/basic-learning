package org.example.basic.oj.leetcode.Q509;

/**
 * @author anyuan
 * @since 2021-08-07 22:39
 */
class Solution {

    public int fib(int n) {
        return fib_recursive(n);
    }

    private int fib_recursive(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else if (n == 2) {
            return 1;
        } else {
            return fib_recursive(n - 1) + fib_recursive(n - 2);
        }
    }

    private int fib_loop(int n) {
        int[] fibResults = new int[Math.max(n + 1, 2)];
        fibResults[0] = 0;
        fibResults[1] = 1;
        for (int i = 2; i <= n; i++) {
            fibResults[i] = fibResults[i-1] + fibResults[i-2];
        }
        return fibResults[n];
    }
}
