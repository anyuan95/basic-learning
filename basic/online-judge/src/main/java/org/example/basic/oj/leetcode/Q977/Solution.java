package org.example.basic.oj.leetcode.Q977;

import java.util.Arrays;

/**
 * 有序数组的平方
 * <p>
 * 给定有序整数数组，要求返回升序平方数组
 *
 * @author anyuan
 * @since 2021-08-12 18:10
 */
class Solution {
    public int[] sortedSquares(int[] nums) {
        int[] answer = new int[nums.length];
        int left = 0, right = nums.length - 1;
        int answerIndex = nums.length - 1;
        boolean leftGreaterEquals;
        while (left <= right) {
            leftGreaterEquals = Math.abs(nums[left]) >= Math.abs(nums[right]);
            if (leftGreaterEquals) {
                // 左边的数大于绝对值更大，先放左边的
                answer[answerIndex--] = nums[left] * nums[left++];
            } else {
                // 否则先放右边的
                answer[answerIndex--] = nums[right] * nums[right--];
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.sortedSquares(new int[]{-7, -3, 2, 3, 11})));
    }
}
