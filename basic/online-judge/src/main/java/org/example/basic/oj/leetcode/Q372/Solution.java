package org.example.basic.oj.leetcode.Q372;

/**
 * @author anyuan
 * @since 2021-12-06 23:44
 */
class Solution {
    /**
     * 其实也是快速幂的思路，只不过二进制变成了十进制
     *
     * @param a
     * @param b
     * @return
     */
    public int superPow(int a, int[] b) {
        return xpow(a, b, b.length - 1);
    }

    private int xpow(int a, int[] b, int index) {
        if (index == -1) {
            return 1;
        }
        return pow(xpow(a, b, index - 1), 10) * pow(a, b[index]) % MOD;
    }

    public static final Integer MOD = 1337;

    private int pow(int a, int times) {
        a %= MOD;
        int answer = 1;
        while (times > 0) {
            answer = (a * answer) % MOD;
            times--;
        }
        return answer;
    }
}
