package org.example.basic.oj.leetcode.Q1748;

class Solution {
    /**
     * 给你一个整数数组 nums 。数组中唯一元素是那些只出现 恰好一次 的元素。
     * <p>
     * 请你返回 nums 中唯一元素的 和 。
     * <p>
     * 1 <= nums.length <= 100
     * 1 <= nums[i] <= 100
     *
     * @param nums
     * @return
     */
    public int sumOfUnique(int[] nums) {
        int[] occurrences = new int[101];
        for (int num : nums) {
            occurrences[num]++;
        }
        int answer = 0;
        for (int i = 0; i < occurrences.length; i++) {
            if (occurrences[i] == 1) {
                answer += i;
            }
        }
        return answer;
    }
}
