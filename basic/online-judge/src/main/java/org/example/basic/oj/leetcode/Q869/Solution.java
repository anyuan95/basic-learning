package org.example.basic.oj.leetcode.Q869;

import java.util.Arrays;
import java.util.HashSet;

/**
 * 最基本的处理方式：打表+词频统计
 * 还有其他方式：打表+DFS，
 *
 * @author anyuan
 * @date 2021-10-28 10:06
 */
class Solution {
    private static HashSet<int[]> powerOf2Digits = new HashSet<>();

    static {
        for (int i = 0; i <= 31; i++) {
            int powerOf2 = 1 << i;
            int[] counts = new int[10];
            // 2^n最后一位一定不是0，不用特殊处理0了
            while (powerOf2 > 0) {
                counts[powerOf2 % 10]++;
                powerOf2 /= 10;
            }
            powerOf2Digits.add(counts);
        }
    }

    public boolean reorderedPowerOf2(int n) {
        int[] counts = new int[10];
        while (n > 0) {
            counts[n % 10]++;
            n /= 10;
        }
        return powerOf2Digits.stream().anyMatch(ints -> Arrays.equals(ints, counts));
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.reorderedPowerOf2(1));

//        int[] i1 = {0,1,0};
//        int[] i2 = {0,1,0};
//        final HashSet<int[]> set = new HashSet<>();
//        set.add(i1);
//        System.out.println(set.contains(i2));

    }
}
