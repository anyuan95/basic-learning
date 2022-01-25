package org.example.basic.oj.leetcode.Q1688;

/**
 * @author anyuan
 * @date 2022-01-25 21:11
 */
class Solution {
    /**
     * 纯暴力解法
     *
     * @param n
     * @return
     */
    public int _numberOfMatches(int n) {
        int answer = 0;
        while (n != 0 && n != 1) {
            answer += n / 2;
            n = (n >> 1) + (n & 1);
        }
        return answer;
    }

    /**
     * 数学归纳法，或者通过思考可以得到
     * 数学归纳的方式：
     * - 当剩余队伍数量不小于2时，一定会有：每经过一次比较，就会减少一个队伍，所以可以得到等式：f(n) = 1 + f(n-1)
     * - f(2) = 1
     * - 将等式进行递推可得，f(n) = 1 + f(n-1) = 1 + 1 + f(n-2) = ...，相当于从n倒数到2，一共是n-1个数，所以结果应该是n-1
     *
     * 思考的方式：
     * - 一共有n个队伍，而这n个队伍不断比较，会导致最后一定只剩下一个队伍，而每次比较只会减少一个队伍，那么从n个队伍到1个队伍，一共刷掉了n-1个队伍，
     *   所以就是一共比较了n-1次
     *
     * @param n
     * @return
     */
    public int numberOfMatches(int n) {
        return n - 1;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.numberOfMatches(7));
        System.out.println(solution.numberOfMatches(14));
    }
}