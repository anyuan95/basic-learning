package org.example.basic.oj.leetcode.Q62;

/**
 * @author anyuan
 * @date 2021-10-13 21:18
 */
class Solution_combination {
    /**
     * 思路：由于只能向右或者向下，所以一共一定需要走m+n-2步；
     * 在m+n-2步中，一定有m-1步是向右的，一定有n-1步是向下的；
     * 所以，实际上题目就相当于是在求C(m+n-2,m-1)
     *
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m, int n) {
        // C(m+n-2,m-1)
        int smaller = Math.min(m, n);
        long dividend = 1, divisor = 1;
        for (int i = 0; i < smaller - 1; i++) {
            dividend *= m + n - 2 - i;
            divisor *= i + 1;
        }
        return (int) (dividend / divisor);
    }

    public static void main(String[] args) {
        final Solution_combination solution = new Solution_combination();
        System.out.println(solution.uniquePaths(3, 7));
    }
}
