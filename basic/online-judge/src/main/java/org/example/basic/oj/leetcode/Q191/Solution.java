package org.example.basic.oj.leetcode.Q191;

/**
 * @author anyuan
 * @since 2021-08-25 13:52
 */
class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
//        return Integer.bitCount(n);
        int bitCount = 0;
        while (n != 0) {
            bitCount += (n & 1);
            n >>>= 1;
        }
        return bitCount;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.hammingWeight(Integer.MIN_VALUE));
        System.out.println(Integer.toBinaryString(Integer.MIN_VALUE));
        System.out.println(solution.hammingWeight(Integer.MAX_VALUE));
        System.out.println(Integer.toBinaryString(Integer.MAX_VALUE));
    }
}
