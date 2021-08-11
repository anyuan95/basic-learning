package org.example.basic.oj.leetcode.Q264;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 第n个丑数
 *
 * @author anyuan
 * @since 2021-08-09 23:39
 */
class Solution {
    public int nthUglyNumber(int n) {
        final int[] primeFactors = {2, 3, 5};
        final HashSet<Long> set = new HashSet<>();
        final PriorityQueue<Long> queue = new PriorityQueue<>();
        set.add(1L);
        queue.add(1L);
        for (int i = 1; i < n; i++) {
            final Long current = queue.remove();
            for (int primeFactor : primeFactors) {
                final Long temp = current * primeFactor;
                if (!set.contains(temp)) {
                    set.add(temp);
                    queue.add(temp);
                }
            }
        }
        return (int) ((long) queue.remove());
    }

    private List<Long> buildUglyNumbers(int times) {
        final int[] primeFactors = {2, 3, 5};
        final List<Long> list = new ArrayList<>();
        final HashSet<Long> set = new HashSet<>();
        final Queue<Long> queue = new PriorityQueue<>();
        queue.add(1L);
        set.add(1L);
        list.add(1L);
        for (int i = 1; i <= times; i++) {
            final Long current = queue.remove();
            for (int primeFactor : primeFactors) {
                final Long temp = current * primeFactor;
                if (!set.contains(temp)) {
                    set.add(temp);
                    queue.add(temp);
                    list.add(temp);
                }
            }
        }
        return list.stream().distinct().sorted(Comparator.comparingLong(o -> o)).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        final List<Long> list = solution.buildUglyNumbers(1407);
        int n = 1407;
        System.out.println(list.get(n - 1));
        System.out.println(solution.nthUglyNumber(n));
    }
}
