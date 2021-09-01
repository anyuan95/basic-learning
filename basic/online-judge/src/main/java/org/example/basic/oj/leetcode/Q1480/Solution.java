package org.example.basic.oj.leetcode.Q1480;

/**
 * ？？？？？？极简题？就求前缀和？
 * @author anyuan
 * @since 2021-08-28 16:42
 */
class Solution {

    public int[] runningSum(int[] nums) {
        int[] answer = new int[nums.length];
        answer[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            answer[i] = answer[i-1] + nums[i];
        }
        return answer;
    }
}
