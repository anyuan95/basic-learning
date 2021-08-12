package org.example.basic.oj.leetcode.Q189;

import java.util.Arrays;

/**
 * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
 *
 * @author anyuan
 * @since 2021-08-12 18:18
 */
class Solution {
    private void rotate(int[] nums, int k) {
        if (nums.length == 1 || k % nums.length == 0) {
            return;
        }
        final int length = nums.length;
        for (int i = 0; i < length; i++) {
            swap(nums, i, (i + k) % length);
        }
    }

    private void swap(int[] nums, int index1, int index2) {
        if (index1 == index2) return;
        nums[index1] = nums[index1] ^ nums[index2];
        nums[index2] = nums[index1] ^ nums[index2];
        nums[index1] = nums[index1] ^ nums[index2];
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        final int[] nums = {1, 2, 3, 4, 5, 6, 7};
        solution.rotate(nums, 3);
        System.out.println(Arrays.toString(nums));
    }
}
