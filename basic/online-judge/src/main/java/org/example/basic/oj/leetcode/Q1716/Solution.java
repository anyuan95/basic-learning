package org.example.basic.oj.leetcode.Q1716;

class Solution {
    public int totalMoney(int n) {
        final int weeks = n / 7;
        // n % 7 = n - weeks * 7
        return (28 + 28 + 7 * (weeks - 1)) * weeks / 2
                        + (weeks + 1 + weeks + 1 + n % 7 - 1) * (n % 7) / 2;

//        (49 + weeks * 7) * weeks / 2
//                + (weeks * 2 + 1 + n - weeks * 7) * (n - weeks * 7) / 2
//                 (n + 1- weeks * 5) * (n-weeks*7) / 2
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.totalMoney(4));
        System.out.println(solution.totalMoney(10));
        System.out.println(solution.totalMoney(20));
    }
}
