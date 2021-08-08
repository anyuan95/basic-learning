package org.example.basic.oj.leetcode.Q70;

/**
 * @author anyuan
 * @since 2021-08-07 22:46
 */
class Solution {
    public int climbStairs(int n) {
        return betterLoop(n);
    }

    private int recursive(int n) {
        if (n == 1) {
            return 1;
        } else if (n == 2) {
            return 1;
        } else {
            return recursive(n - 1) + recursive(n - 2);
        }
    }

    private int loop(int n) {
        int[] results = new int[Math.max(3, n+1)];
        results[1] = 1;
        results[2] = 2;
        for (int i = 3; i <= n; i++) {
            results[i] = results[i-1] + results[i-2];
        }
        return results[n];
    }

    private int betterLoop(int n) {
        int lastLastResult = 1, lastResult = 2;
        if (n==1) return 1;
        if (n==2) return 2;
        for (int i = 3; i <= n; i++) {
            lastResult = lastResult + lastLastResult;
            lastLastResult = lastResult - lastLastResult;
        }
        return lastResult;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
//        System.out.println(solution.climbStairs(45));
        for (int i = 1; i <= 30; i++) {
            if (solution.loop(i) != solution.betterLoop(i)) {
                System.out.println(i);
                return;
            }
        }
        System.out.println(solution.climbStairs(4));
    }
}
