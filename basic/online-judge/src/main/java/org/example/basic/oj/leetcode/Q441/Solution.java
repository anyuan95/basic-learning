package org.example.basic.oj.leetcode.Q441;

import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author anyuan
 * @since 2021-10-10 17:02
 */
class Solution {
    /**
     * 实际上就是给定一个数字s，这个s是1-n的和，求这个n最大是多少
     * (1+n)*n/2
     *
     * @param n
     * @return
     */
    public int arrangeCoins(int n) {
        return (int) ((Math.sqrt(8.0 * n + 1) - 1) / 2);
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        final Random random = new Random();
        Function<Integer, Integer> f = (a) -> (int) ((long) a * (a + 1) / 2);
        for (int i = 1; i < 100; i++) {
            final int n = Math.abs(random.nextInt());
            final int arr = solution.arrangeCoins(n);
            if (n >= f.apply(arr) && n < f.apply(arr + 1)) {
            } else {
                System.out.println("n = " + n + ", arr = " + arr);
                return;
            }
        }
    }
}
