package org.example.basic.oj.leetcode.Q1447;

import java.util.ArrayList;
import java.util.List;

class Solution {
    /**
     * 最暴力的解法，计算每个比n小的值的所有最简真分数
     *
     * @param n
     * @return
     */
    public List<String> _simplifiedFractions(int n) {
        final List<String> answer = new ArrayList<>();
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                if (gcd(i, j) == 1) {
                    answer.add(j + "/" + i);
                }
            }
        }
        return answer;
    }

    /**
     * 保证a>=b
     *
     * @param a
     * @param b
     * @return
     */
    public int gcd(int a, int b) {
       return b == 0 ? a : gcd(b, a % b);
    }

    /**
     * 上面的解法是先选定分母，然后找GCD=1的分子。
     * 现在掉过来，改成先确定分子，然后确定分母
     *
     * 例如，分子为2时，可以做分母的数只有1+2k
     * 分子为3时，可以做分母的数只有1+3k和2+3k
     * 分子为4时，可以做分母的数只有1+4k,3+4k
     * 分子为5~
     * 分子为6时，可以做分母的数只有1+6k,5+6k
     * 分子为7时，可以做分母的数有1~6+7k
     *
     *
     * @param n
     * @return
     */
    public List<String> simplifiedFractions(int n) {
        final List<String> answer = new ArrayList<>();
        for (int i = 1; i < n; i++) {
            for (int j = i; j <= n; j++) {
                //TODO
                answer.add(i + "/" + j);

            }
        }
        return answer;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution._simplifiedFractions(6));
    }

}
