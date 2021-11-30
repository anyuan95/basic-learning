package org.example.basic.oj.leetcode.Q458;

/**
 * @author anyuan
 * @since 2021-11-25 23:20
 */
class Solution {
    /**
     * 这个题目实际上属于信息论
     *
     * 思路：
     * 原理和小白鼠问题是一样的
     * 就是通过进制的思路，把毒药转为二进制表示，假设二进制位数为n，那么就找n个小白鼠，每个小白鼠喝下二进制位上为1的药水。
     * 最后死的小白鼠，转换成十进制，就是有毒的药水编号。
     *
     * 本题求指定轮次内，使用的最少小白鼠数量
     * 实际上就相当于，假设最多有k轮，那么把总数转换成k+1进制，若第x位为i，则在第i轮喂给x小白鼠这个药水
     *
     *
     * @param buckets
     * @param minutesToDie
     * @param minutesToTest
     * @return
     */
    public int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
        final int digit = minutesToTest / minutesToDie + 1;
        return (int) Math.ceil(Math.log(buckets) / Math.log(digit));
    }
}
