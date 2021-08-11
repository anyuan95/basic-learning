package org.example.basic.oj.leetcode.Q313;

import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * @author anyuan
 * @since 2021-08-10 00:14
 */
class Solution {
    public int nthSuperUglyNumber(int n, int[] primes) {
        final HashSet<Long> set = new HashSet<>();
        final PriorityQueue<Long> queue = new PriorityQueue<>();
        set.add(1L);
        queue.add(1L);
        for (int i = 1; i < n; i++) {
            final Long current = queue.remove();
            for (int primeFactor : primes) {
                final Long temp = current * primeFactor;
                if (!set.contains(temp)) {
                    set.add(temp);
                    queue.add(temp);
                }
            }
        }
        return (int) ((long) queue.remove());
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.nthSuperUglyNumber(12, new int[]{2, 7, 13, 19}));
    }
}
