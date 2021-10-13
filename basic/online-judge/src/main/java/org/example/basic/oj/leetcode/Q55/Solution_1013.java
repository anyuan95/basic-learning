package org.example.basic.oj.leetcode.Q55;

/**
 * @author: anyuan
 * @Date: 2021-10-13 20:17
 */
class Solution_1013 {
    public boolean canJump(int[] nums) {
        // 如果只有一个格，那起点就是终点了
        if (nums.length == 1) {
            return true;
        }
        int n = nums.length;
        int maxReachable = nums[0];
        for (int i = 1; i <= maxReachable; i++) {
            // 不断更新当前能到达的最远位置，然后再看从这里面的位置开始向后跳，看最多能跳多远
            if (i + nums[i] >= n - 1) {
                return true;
            }
            maxReachable = Math.max(i + nums[i], maxReachable);
        }
        return false;
    }

    public static void main(String[] args) {
        Solution_1013 solution = new Solution_1013();
        System.out.println(solution.canJump(new int[]{0}));
        System.out.println(solution.canJump(new int[]{1, 0}));
        System.out.println(solution.canJump(new int[]{3, 2, 1, 0, 4}));
        System.out.println(solution.canJump(new int[]{2, 3, 1, 1, 4}));
    }
}
