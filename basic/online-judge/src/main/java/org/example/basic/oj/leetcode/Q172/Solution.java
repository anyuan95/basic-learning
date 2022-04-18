package org.example.basic.oj.leetcode.Q172;

/**
 * @author anyuan
 * @date 2022-03-31 21:11
 */
class Solution {
    /**
     * 求阶乘结果末尾的0个数
     *
     * 可以认为，所有的0都是由5*2得到的
     * 即，将1-n之间所有值做因数分解，因数分解结果中5和2之间最小的个数就是0的个数
     * 那么，1-n之间有多少个因数5？
     * 因数5的总数等于 n/5向下取整 + n/(5^2)向下取整 + n/(5^3)向下取整 + ...
     *
     *
     *
     * @param n
     * @return
     */
    public int trailingZeroes(int n) {
        return n < 5 ? 0 : n / 5 + trailingZeroes(n / 5);
    }
}
