package org.example.basic.oj.leetcode.Q2016;

/**
 * 给你一个下标从 0 开始的整数数组 nums ，该数组的大小为 n ，请你计算 nums[j] - nums[i] 能求得的 最大差值 ，其中 0 <= i < j < n 且 nums[i] < nums[j] 。
 * <p>
 * 返回 最大差值 。如果不存在满足要求的 i 和 j ，返回 -1 。
 *
 * @author anyuan
 * @date 2022-02-26 21:51
 */
class Solution {

    /**
     * 思路：从左到右遍历，遍历过程中不断更新最小值
     * 如果前面的最小值小于当前位置值，那就有一个可能的结果
     *
     * @param nums
     * @return
     */
    public int maximumDifference(int[] nums) {
        final int n = nums.length;
        int min = nums[0], answer = 0;
        for (int i = 1; i < n; i++) {
            if (min < nums[i]) {
                answer = Math.max(answer, nums[i] - min);
            } else {
                min = Math.min(min, nums[i]);
            }
        }
        return answer <= 0 ? -1 : answer;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.maximumDifference(new int[]{7, 1, 5, 4}));
    }
}
