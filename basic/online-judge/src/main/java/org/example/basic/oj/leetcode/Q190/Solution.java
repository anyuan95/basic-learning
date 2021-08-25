package org.example.basic.oj.leetcode.Q190;

/**
 * @author anyuan
 * @since 2021-08-25 14:24
 */
class Solution {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int answer = 0;
        for (int i = 0; i < 32; i++) {
            answer <<= 1;
            answer |= (n & 1);
            n >>>= 1;
        }
        return answer;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(Integer.reverse(0b00000010100101000001111010011100));
        System.out.println(solution.reverseBits(0b00000010100101000001111010011100));
    }
}
