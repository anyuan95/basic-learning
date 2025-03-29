package org.example.basic.oj.leetcode.Q2829;

class Solution {
    public int minimumSum(int n, int k) {
        int part1End = k / 2;
        int result = sn(1, Math.min(n, part1End));
        if (part1End < n) {
            result += sn(k, k + n - part1End - 1);
        }
        return result;
    }

    private int sn(int a1, int an) {
        int n = an - a1 + 1;
        return (a1 + an) * n / 2;
    }
}