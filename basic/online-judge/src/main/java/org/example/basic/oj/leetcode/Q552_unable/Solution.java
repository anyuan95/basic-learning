package org.example.basic.oj.leetcode.Q552_unable;

/**
 * @author anyuan
 * @since 2021-08-17 09:25
 */
class Solution {
    /**
     * 思路：
     * 求出全排列，然后去掉无法获得奖励的排列方式
     * <p>
     * n天的全排列可能性总数：3^n
     * <p>
     * 无法获得奖励的排列方式：
     * 1.2次及以上的缺勤（A）
     * 2.连续3天及以上的迟到（L）
     *
     * @param n
     * @return
     */
    public int checkRecord(int n) {
        // 两个是A，其余是任意
        long get2Absent = (long) (Math.pow(3, n - 2));
        // 连续的LLL，其余位置无所谓
        long continuous3Late = (long) (Math.pow(3, n - 3));
        // 连续LLL，任意两个A
        long intersection = (long) (Math.pow(3, n - 5));

        return -1;
    }
}
