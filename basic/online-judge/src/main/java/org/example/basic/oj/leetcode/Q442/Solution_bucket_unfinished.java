package org.example.basic.oj.leetcode.Q442;

import java.util.ArrayList;
import java.util.List;

/**
 * @author anyuan
 * @date 2021-10-18 14:24
 */
 class Solution_bucket_unfinished {
    /**
     * 思路：
     * 要求原地找，取巧把原数组改成桶
     * 还是从0位置开始遍历，每遍历到一个值，就在这个值对应index上进行计数（从n开始，避免和原元素冲突）
     *
     * 没写出来
     *
     *
     * @param nums
     * @return
     */
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> answer = new ArrayList<>();
        // 先把数组改成桶
        for (int i = 0; i < nums.length; i++) {
            makeBucket(nums, i);
        }
        return answer;
    }

    private void makeBucket(int[] nums, int index) {
        final int n = nums.length;
        while (nums[index] < n) {
            final int curVal = nums[index];
            boolean needNext = true;
            if (nums[curVal - 1] < n) {
                needNext = false;
                index = nums[curVal - 1];
                nums[curVal - 1] = n;
            }
            nums[curVal - 1] ++;
            if (!needNext) {
                return;
            }
        }
    }

    public static void main(String[] args) {
        final Solution_bucket_unfinished solution = new Solution_bucket_unfinished();
        System.out.println(solution.findDuplicates(new int[]{4, 3, 2, 7, 8, 2, 3, 1}));
    }
}
