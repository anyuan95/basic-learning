package org.example.basic.oj.leetcode.Q209;

/**
 * @author anyuan
 * @since 2021-09-23 11:37
 */
class Solution_0923 {
    public int minSubArrayLen(int target, int[] nums) {
        int sum = 0;
        int startIndex = -1;
        int minLen = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= target) {
                return 1;
            }
            if (startIndex == -1) {
                startIndex = i;
            }

            sum += nums[i];
            // 如果sum小于target，就继续遍历，继续加
            // 如果sum大于等于target，就尝试减掉开头的值
            if (sum >= target) {
                // 从头开始减掉，使得数组尽可能地小
                while (sum - nums[startIndex] >= target) {
                    sum -= nums[startIndex++];
                }
                minLen = Math.min(i - startIndex + 1, minLen);
            }
        }
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }

    public static void main(String[] args) {
        final Solution_0923 solution = new Solution_0923();
        System.out.println(solution.minSubArrayLen(7, new int[]{2, 3, 1, 2, 4, 3}));
        System.out.println(solution.minSubArrayLen(4, new int[]{1, 4, 4}));
        System.out.println(solution.minSubArrayLen(11, new int[]{1, 1, 1, 1, 1, 1, 1, 1}));
    }
}
