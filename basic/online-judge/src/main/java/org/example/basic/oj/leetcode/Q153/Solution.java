package org.example.basic.oj.leetcode.Q153;

/**
 * @author anyuan
 * @since 2021-09-02 15:27
 */
class Solution {
    /**
     * 二分查找旋转数组中的最小值
     *
     * @param nums
     * @return
     */
    public int findMin(int[] nums) {
        int n = nums.length, left = 0, right = n - 1, middle;
        if (nums[0] <= nums[n - 1]) {
            // 有可能旋转了n次之后，和原始顺序一致了。又由于原始数组是升序，所以最小值一定是在最左侧
            return nums[0];
        }
        while (left <= right) {
            middle = left + ((right - left) >> 1);
            if (middle > 0 && nums[middle] < nums[middle - 1]) {
                // 上一个数比当前数大，则这个就是倒序开始的位置，middle就是最小值
                return nums[middle];
            }
            if (nums[left] > nums[right]) {
                if (nums[middle] < nums[left]) {
                    // left > middle，说明这一段是倒序的
                    right = middle - 1;
                } else if (nums[middle] > nums[right]) {
                    // middle > right，说明倒序位置在这段中
                    left = middle + 1;
                }
            } else {
                // 如果l-r已经是有序了？
                return nums[left];
            }
        }
        return Integer.MIN_VALUE;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.findMin(new int[]{3, 4, 5, 1, 2}));
        System.out.println(solution.findMin(new int[]{4,5,6,7,0,1,2}));
        System.out.println(solution.findMin(new int[]{11,13,15,17}));
        System.out.println(solution.findMin(new int[]{2,1}));
    }
}
