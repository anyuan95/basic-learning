package org.example.basic.oj.leetcode.Q35;

/**
 * 给定长度n的升序数组，目标值target
 * 如果target在数组中，返回其下标；如果不在，返回应该插入的位置
 *
 * @author anyuan
 * @since 2021-08-12 18:02
 */
class Solution {
    /**
     * 注意下标越界问题
     *
     * @param nums
     * @param target
     * @return
     */
    public int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length - 1, middle;
        while (left <= right) {
            middle = left + ((right - left) >> 1);
            if (nums[middle] == target) {
                return middle;
            } else if (nums[middle] < target) {
                if (middle == nums.length - 1 || target < nums[middle + 1]) {
                    return middle + 1;
                }
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        return 0;
    }
}
