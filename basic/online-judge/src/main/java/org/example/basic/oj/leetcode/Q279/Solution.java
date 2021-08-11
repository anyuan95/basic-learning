package org.example.basic.oj.leetcode.Q279;

/**
 * 给定一个数，求它最少可以由多少个完全平方数相加获得。
 *
 * @author anyuan
 * @since 2021-08-11 09:21
 */
class Solution {
    /**
     * 从头开始打表，计算从1-n的每个值，最少是多少个完全平方数的和。
     * 计算方式：
     *   每个数k一定能拆分成m^2和一个数a的和 （限制m*n<=n）
     *   又因为a先前一定已经计算出来了，
     *   所以f(n)=min(f(n-m^2))+1 （此处1<=m<=sqrt(n)）
     *
     * 空间复杂度O(N)
     * 时间复杂度O(N*sqrt(N))
     *
     *
     * @param n
     * @return
     */
    public int numSquares(int n) {
        int[] records = new int[n + 1];
        records[0] = 0;

        for (int i = 1; i <= n; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 1; j * j <= i; j++) {
                min = Math.min(min, records[i - j * j] + 1);
            }
            records[i] = min;
        }
        return records[n];
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.numSquares(12));
        System.out.println(solution.numSquares(13));
    }

}
