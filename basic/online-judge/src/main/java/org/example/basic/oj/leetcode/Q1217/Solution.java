package org.example.basic.oj.leetcode.Q1217;

class Solution {
    public int minCostToMoveChips(int[] position) {
        // 把所有奇数的放一起，所有偶数的放一起，哪个少哪个就是最小代价
        int s1 = 0, s2 = 0;
        for (int i : position) {
            if (i % 2 == 0) {
                s1++;
            } else {
                s2++;
            }
        }
        return Math.min(s1, s2);
    }
}
