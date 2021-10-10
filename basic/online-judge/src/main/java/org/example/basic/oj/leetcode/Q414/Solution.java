package org.example.basic.oj.leetcode.Q414;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * @author anyuan
 * @since 2021-10-06 19:47
 */
class Solution {
    public int thirdMax(int[] nums) {
        HashSet<Integer> uniqueSet = new HashSet<>();
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(o -> (int) o).reversed());
        for (int num : nums) {
            if (!uniqueSet.contains(num)) {
                queue.add(num);
                uniqueSet.add(num);
            }
        }
        if (uniqueSet.size() < 3) {
            return queue.poll();
        } else {
            queue.poll();
            queue.poll();
            return queue.poll();
        }
    }

    public int thirdMax_tbr(int[] nums) {
        long max1 = Long.MIN_VALUE, max2 = Long.MIN_VALUE, max3 = Long.MIN_VALUE;
        for (int num : nums) {
            if (num > max1) {
                // 更新max1
                max3 = max2;
                max2 = max1;
                max1 = num;
            } else if (num < max1 && num > max2) {
                // 更新max2
                max3 = max2;
                max2 = num;
            } else if (num < max2 && num > max3) {
                // 更新max3
                max3 = num;
            }
        }
        // 题目要求：如果没有第三大的值，那就直接返回最大值
        return (int) (max3 == Long.MIN_VALUE ? max1 : max3);
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.thirdMax(new int[]{-2147483648, 1, 1}));
    }
}
