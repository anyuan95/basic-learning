package org.example.basic.oj.leetcode.Q1984;

import java.util.Arrays;

/**
 * @author anyuan
 * @date 2022-02-11 17:59
 */
class Solution {
    public int minimumDifference(int[] nums, int k) {
        Arrays.sort(nums);
        int answer = Integer.MAX_VALUE;
        final int n = nums.length;
        for (int i = 0; i < n - k + 1; i++) {
            answer = Math.min(answer, nums[i + k - 1] - nums[i]);
        }
        return answer == Integer.MAX_VALUE ? 0 : answer;
    }

    public int _minimumDifference(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length, ans = nums[k - 1] - nums[0];
        for (int i = k; i < n; i++) {
            ans = Math.min(ans, nums[i] - nums[i - k + 1]);
        }
        return ans;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.minimumDifference(new int[]{9, 4, 1, 7}, 1));
        System.out.println(solution.minimumDifference(new int[]{9, 4, 1, 7}, 2));
    }
}
