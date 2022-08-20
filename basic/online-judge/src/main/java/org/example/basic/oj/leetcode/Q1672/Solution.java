package org.example.basic.oj.leetcode.Q1672;

class Solution {
    public int maximumWealth(int[][] accounts) {
        int maxSum = 0;
        for (int i : accounts[0]) {
            maxSum+=i;
        }
        for (int i = 1; i < accounts.length; i++) {
            final int[] nums = accounts[i];
            int temp = 0;
            for (int num : nums) {
                temp+=num;
            }
            if (temp > maxSum) {
                maxSum = temp;
            }
        }
        return maxSum;
    }
}
