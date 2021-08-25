package org.example.basic.oj.leetcode.Q231;

/**
 * 判断一个数是否是2的幂
 *
 * 两种思路：
 * 1.移位，直到遇到1，如果剩下的部分只有1，则就是2的幂
 * 2.所有非正整数都不会是2的幂。对于正整数，如果一个数是2的幂，则其二进制可以表示为1000...0000，则该值减1一定是全1的，二者相与一定等于0
 *
 * @author anyuan
 * @since 2021-08-25 11:37
 */
class Solution {
    public boolean isPowerOfTwo(int n) {
        return n > 0 && ((n & (n - 1)) == 0);
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();

    }
}
