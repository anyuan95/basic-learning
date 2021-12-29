package org.example.basic.oj.leetcode.Q372;

/**
 * @author anyuan
 * @since 2021-12-06 23:44
 */
class Solution {
    /**
     * 其实也是快速幂的思路，只不过二进制变成了十进制
     * <p>
     * 重新整理思路：假设给定的值是n=a*100+b*10+c
     * 那么由于幂的原理，可以有：
     * x^n = x^(a*100+b*10+c) = (x^a)^100 * (x^b)^10 * (x^c)
     * = ((x^a)^10)^10 * (x^b)^10 * (x^c)
     * // 提取公共的幂次
     * = ((x^a)^10 * (x^b))^10 * (x^c)
     * <p>
     * 可以粗略的认为这种方式就是快速幂的变种（只不过执行速度没有普通快速幂快就是了）
     * <p>
     * 标准的快速幂：通过2^n的方式进行，将幂值转化为二进制，从高位到低位逐次处理
     *
     * 确实还可以优化，因为每一位实际上都是做了一次^10，这个步骤还可以进行一次快速幂的优化
     *
     * @param a
     * @param b
     * @return
     */
    public int superPow(int a, int[] b) {
        if (a == 1) {
            return 1;
        }
        int answer = 1;
        final int n = b.length;
        for (int i = 0; i < n; i++) {
            // 上一位的结果作为底数，计算其10次幂，再乘以a的b[i]次幂
            answer = quickPow_highToLow(answer, 10) * quickPow_highToLow(a, b[i]) % MOD;
        }
        return answer;
    }

    private int pow(int base, int times) {
        base %= MOD;
        int answer = 1;
        while (times-- > 0) {
            answer = answer * base % MOD;
        }
        return answer;
    }

    /**
     * 这里的快速幂实际上是在不断地更新底数，而不是通过从高位逐步到低位的顺序进行的
     */
    private int quickPow_lowToHigh(int base, int times) {
        base %= MOD;
        int answer = 1;
        while (times != 0) {
            if ((times & 1) != 0) {
                answer = answer * base % MOD;
            }
            base = base * base % MOD;
            times >>= 1;
        }
        return answer;
    }

    /**
     *  例如，x^10可以拆分为：
     *  x^10 = x^(8+2) = x^0x1010 = (((((x^1)^2)^(x^0))^2)^(x^1))^2)^(x^0)
     *
     * @param base
     * @param times 只支持到4个二进制位
     * @return
     */
    private int quickPow_highToLow(int base, int times) {
        base %= MOD;
        int answer = 1;
        for (int i = 3; i >= 0; i--) {
            // 上一位的结果作为底数，计算其2次幂，再乘以base的这个二进制位的值次幂
            final int theIthDigit = times & (1 << i);
            // 第i位只会是0或者1，所以^[i]只会是1或者base
            answer = (answer * answer % MOD) * ((theIthDigit == 0 ? 1 : base) % MOD) % MOD;
        }
        return answer;
    }

    public static final Integer MOD = 1337;

//    public int _superPow(int a, int[] b) {
//        return xpow(a, b, b.length - 1);
//    }
//
//    private int xpow(int a, int[] b, int index) {
//        if (index == -1) {
//            return 1;
//        }
//        return pow(xpow(a, b, index - 1), 10) * pow(a, b[index]) % MOD;
//    }
//
//    private int pow(int a, int times) {
//        a %= MOD;
//        int answer = 1;
//        while (times > 0) {
//            answer = (a * answer) % MOD;
//            times--;
//        }
//        return answer;
//    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.superPow(2, new int[]{1, 0}));
        System.out.println(solution.superPow(2, new int[]{1, 0, 0}));
        System.out.println(solution.superPow(2147483647, new int[]{2, 0, 0}));
    }
}
