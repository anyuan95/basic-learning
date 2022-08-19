package org.example.basic.oj.leetcode.Q1403;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author anyuan
 * @date 2022-08-04 21:17
 */
class Solution {
    public List<Integer> minSubsequence(int[] nums) {
        Arrays.sort(nums);
        int sum = Arrays.stream(nums).sum();
        int removeSum = 0;
        final List<Integer> answer = new ArrayList<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            removeSum += nums[i];
            sum -= nums[i];
            answer.add(nums[i]);
            if (removeSum > sum) {
                return answer;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.minSubsequence(new int[]{4,3,10,9,8}));
    }
}
