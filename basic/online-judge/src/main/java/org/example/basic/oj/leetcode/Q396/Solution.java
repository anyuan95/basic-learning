package org.example.basic.oj.leetcode.Q396;

class Solution {
    public int maxRotateFunction(int[] nums) {
        int sum = 0, pre = 0;
        final int n = nums.length;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            pre += nums[i] * i;
        }
        int answer = pre;
        // 从前往后推，由前一个推导出后一个
        for (int i = 1; i < n; i++) {
            pre = pre - sum + n * nums[i-1];
            answer = Math.max(answer, pre);
        }
        return answer;
    }
}
