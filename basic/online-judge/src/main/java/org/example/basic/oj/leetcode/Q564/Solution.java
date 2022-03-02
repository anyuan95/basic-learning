package org.example.basic.oj.leetcode.Q564;

import java.util.HashSet;
import java.util.Set;

/**
 * 寻找离目标数最近的回文数
 *
 * @author anyuan
 * @date 2022-03-02 18:48
 */
class Solution {
    /**
     * 暴力思路：找呗，最暴力的方式就是找到比他大的和比它小的，然后看看哪个更近
     * 稍好一点：
     * - 如果n有偶数个位，那就先用高的一半做翻转然后拼接，然后用高的一半减一翻转拼接，然后找比较近的
     * - 如果n有奇数个位，那就直接沿着中间值做对称
     * <p>
     * ----------
     * 对于任意一个s，可能的值有5种：
     * (假定s的前一半是s')
     * s''s、(s'+1)(1+'s)、(s'-1)(1-s')、比他少一位的9999、比他多一位的100001
     *
     * @param s
     * @returnl
     */
    public String nearestPalindromic(String s) {
        final int n = s.length();
        final long cur = Long.parseLong(s);
        final Set<Long> set = new HashSet<>();
        set.add((long) Math.pow(10, (n - 1)) - 1);
        set.add((long) Math.pow(10, n) + 1);
        long t = Long.parseLong(s.substring(0, (n + 1) / 2));
        for (long i = t - 1; i <= t + 1; i++) {
            final long temp = gen(i, n % 2 == 0);
            if (temp != cur) {
                set.add(temp);
            }
        }
        long answer = -1;
        for (Long num : set) {
            if (answer == -1) {
                answer = num;
            } else if (Math.abs(num - cur) < Math.abs(answer - cur)) {
                answer = num;
            } else if (Math.abs(num - cur) == Math.abs(answer - cur) && num < answer) {
                answer = num;
            }
        }
        return String.valueOf(answer);
    }

    /**
     * 把k翻过来，拼到k后边
     *
     * @param k
     * @param isEven
     * @return
     */
    private long gen(long k, boolean isEven) {
        final StringBuilder builder = new StringBuilder();
        builder.append(k);
        final int n = builder.length();
        int index = isEven ? n - 1 : n - 2;
        while (index >= 0) {
            builder.append(builder.charAt(index--));
        }
        return Long.parseLong(builder.toString());
    }

}