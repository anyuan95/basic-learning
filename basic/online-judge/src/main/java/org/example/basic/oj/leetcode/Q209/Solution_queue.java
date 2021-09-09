package org.example.basic.oj.leetcode.Q209;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author anyuan
 * @since 2021-09-05 15:04
 */
class Solution_queue {
    /**
     * 用队列
     * 在队列中保存数据，保证队列中的数据的和小于target
     *
     * 因为使用了队列，所以更慢了
     *
     *
     * @param target
     * @param nums
     * @return
     */
    public int minSubArrayLen(int target, int[] nums) {
        Queue<Integer> queue = new LinkedList<>();
        int answer = Integer.MAX_VALUE, n = nums.length;
        int currentSum = 0;
        for (int num : nums) {
            if (num >= target) {
                return 1;
            }
            currentSum += num;
            queue.offer(num);
            // 保证队列中的元素和小于target
            while (currentSum >= target) {
                answer = Math.min(answer, queue.size());
                currentSum -= queue.poll();
            }
        }
        return answer == Integer.MAX_VALUE ? 0 : answer;
    }

    public static void main(String[] args) {
        final Solution_queue solution = new Solution_queue();
        System.out.println(solution.minSubArrayLen(7, new int[]{2, 3, 1, 2, 4, 3}));
        System.out.println(solution.minSubArrayLen(7, new int[]{2, 3, 1, 2, 4, 3, 8}));
        System.out.println(solution.minSubArrayLen(6, new int[]{2, 2, 2, 2, 2, 2}));
        System.out.println(solution.minSubArrayLen(60, new int[]{2, 2, 2, 2, 2, 2}));
    }
}
