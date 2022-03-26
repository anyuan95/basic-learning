package org.example.basic.oj.leetcode.Q1220;

class Solution {
    private static final int MOD = (int) (1e9 + 7);

    /**
     * 思路：每次统计以a为首的字符串数量
     * 由于题目中描述，a后面只能跟着e，换种描述方式就是，只有e的前面才能是a
     * 相当于模拟不断向头部插入字母的处理方式
     *
     * @param n
     * @return
     */
    public int countVowelPermutation(int n) {
        long a = 1, e = 1, i = 1, o = 1, u = 1;
        for (int index = 2; index <= n; index++) {
            long aa = e % MOD;
            long ee = (a + i) % MOD;
            long ii = (a + e + o + u) % MOD;
            long oo = (i + u) % MOD;
            long uu = a % MOD;
            a = aa;
            e = ee;
            i = ii;
            o = oo;
            u = uu;
        }
        return (int) ((a + e + i + o + u) % MOD);
    }
}
