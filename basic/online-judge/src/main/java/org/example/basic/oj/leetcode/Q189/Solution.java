package org.example.basic.oj.leetcode.Q189;

import java.util.Arrays;

/**
 * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
 *
 * @author anyuan
 * @since 2021-08-12 18:18
 */
class Solution {
    /**
     * 相当于做三次整体翻转
     * 例如，nums=[1,2,3,4,5,6,7],k=4，则期望结果为[4,5,6,7,1,2,3]
     * 相当于：
     * 1.先将nums从0到length-1整体翻转
     * 2.然后将nums从0到k-1整体翻转
     * 3.最后将k到length-1整体翻转
     *
     * @param nums
     * @param k
     */
    private void rotate(int[] nums, int k) {
        if (nums.length <= 1 || k % nums.length == 0) {
            return;
        }
        k = k % nums.length;
        _rotate(nums, 0, nums.length - 1);
        _rotate(nums, 0, k - 1);
        _rotate(nums, k, nums.length - 1);
    }

    private void _rotate(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start++, end--);
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
