package org.example.basic.oj.leetcode.Q209;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author anyuan
 * @since 2021-09-23 15:33
 */
class Solution_queue_0923 {
    public int minSubArrayLen(int target, int[] nums) {
        Queue<Integer> queue = new LinkedList<>();
        int sum = 0, minLen = Integer.MAX_VALUE;
        for (int num : nums) {
            queue.offer(num);
            sum += num;
            while (!queue.isEmpty() && sum >= target) {
                minLen = Math.min(minLen, queue.size());
                sum -= queue.poll();
            }
        }
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }

    public int minSubArrayLen_queuelike(int target, int[] nums) {
        // 使用left和right模拟队列
        // 实际上也是滑动窗口的思路，就相当于是右进左出出出
        int sum = 0, minLen = Integer.MAX_VALUE, left = 0, right = 0;
        while (right < nums.length) {
            sum += nums[right++];
            while (sum >= target) {
                minLen = Math.min(minLen, right - left);
                sum -= nums[left++];
            }
        }
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }


    public static void main(String[] args) {
        final Solution_queue_0923 solution = new Solution_queue_0923();
        System.out.println(solution.minSubArrayLen(7, new int[]{2, 3, 1, 2, 4, 3}));
        System.out.println(solution.minSubArrayLen(4, new int[]{1, 4, 4}));
        System.out.println(solution.minSubArrayLen(11, new int[]{1, 1, 1, 1, 1, 1, 1, 1}));
        System.out.println(solution.minSubArrayLen(15, new int[]{1, 2, 3, 4, 5}));
    }
}
