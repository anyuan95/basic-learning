package org.example.basic.oj.leetcode.O45;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 将给定的非负整数数组中所有元素进行字符串拼接，求最小的结果。
 *
 * @author anyuan
 * @since 2021-08-13 16:23
 */
class Solution {
    /**
     * 其实这道题是个动态规划问题。
     * 只要能想到，并证明出来，coding没有难度。
     *
     * 重点：对于字符串a和b，按照a+b<b+a的方式排序，最后全部拼装后的结果一定是最小字典序的。
     * 证明：
     * 1.a+b<b+a有传递性；
     * 2.以此方式排序的数组中，任意两个元素交换顺序，得到的结果都会是大于等于原始结果的；
     *
     *
     * @param nums
     * @return
     */
    public String minNumber(int[] nums) {
        return Arrays.stream(nums)
                .mapToObj(String::valueOf)
                .sorted((o1, o2) -> (o1 + o2).compareTo((o2 + o1)))
                .collect(Collectors.joining());
    }
}
