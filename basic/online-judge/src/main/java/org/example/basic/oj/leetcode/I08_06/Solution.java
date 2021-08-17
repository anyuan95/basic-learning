package org.example.basic.oj.leetcode.I08_06;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 汉诺塔问题
 *
 * @author anyuan
 * @since 2021-08-17 11:02
 */
public class Solution {
    /**
     * 要求用栈，并没有实现
     *
     * @param A
     * @param B
     * @param C
     */
    public void hanota(List<Integer> A, List<Integer> B, List<Integer> C) {
        move(A.size(), A, C, B);
    }

    public void move(int num, List<Integer> from, List<Integer> to, List<Integer> helper) {
        if (num == 1) {
            to.add(from.remove(from.size() - 1));
            return;
        }
        move(num - 1, from, helper, to);
        to.add(from.remove(from.size() - 1));
        move(num - 1, helper, to, from);
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        final List<Integer> a = Lists.newArrayList(2, 1, 0);
        final List<Integer> b = Lists.newArrayList();
        final List<Integer> c = Lists.newArrayList();
        solution.hanota(a, b, c);
        System.out.println(c);
    }
}
