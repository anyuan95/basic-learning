package org.example.basic.oj.leetcode.Q1005;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author anyuan
 * @since 2021-12-03 00:01
 */
class Solution {
    public int largestSumAfterKNegations(int[] nums, int k) {
        final PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> Math.abs(o2) - Math.abs(o1));
        int sum = 0;
        final int n = nums.length;
        for (int num : nums) {
            queue.add(num);
        }

        int index = 0;
        // 分开处理，把前面能加的全都加上
        while (index < n - 1) {
            final Integer poll = queue.poll();
            // 仅当还有翻转次数时，才可以进行翻转
            if (k > 0 && poll < 0) {
                k--;
                sum -= poll;
            } else {
                sum += poll;
            }
            index++;
        }
        // 最后一定只剩了一个。如果此时还有k次翻转机会，那就看是奇数次还是偶数次了
        sum += k % 2 == 0 ? queue.poll() : -queue.poll();
        return sum;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.largestSumAfterKNegations(new int[]{4, 2, 3}, 1));
        System.out.println(solution.largestSumAfterKNegations(new int[]{3, -1, 0, 2}, 3));
        System.out.println(solution.largestSumAfterKNegations(new int[]{2, -3, -1, 5, -4}, 2));
    }
}
