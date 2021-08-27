package org.example.basic.oj.leetcode.Q33;

/**
 * 被等号条件折磨死
 *
 * @author anyuan
 * @since 2021-08-27 11:05
 */
class Solution {
    /**
     * 要求时间复杂度Ologn
     * <p>
     * 给定的数组，从0到某一个下标k的部分，被放到了k+1~n-1部分的后面
     * 原始的数组是严格升序的
     * 现在要在调整后的数组中查找某个值 的下标
     * <p>
     * 则这个值有几种情况：
     * 1.在0-k之间
     * 2.在k+1-n之间
     * <p>
     * 注意可能出现的问题：可能从0-n位置都移动了，即会导致整个数组都依然是升序的
     * <p>
     * 调整思路：每次将现有数组进行二分，则分割过程中一定至多只有一个部分是乱序的。这个乱序的部分一定是首>尾的
     *
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        final int n = nums.length;
        int left = 0, right = n - 1;
        while (left <= right) {
            int middle = left + ((right - left) >> 1);
            if (nums[middle] == target) {
                return middle;
            }
            if (nums[left] <= nums[middle]) {
                // 说明左边是有序的
                if (target >= nums[left] && target <= nums[middle]) {
                    // target in [left, middle)  说明target在左边部分中
                    right = middle - 1;
                } else {
                    left = middle + 1;
                }
            } else {
                // 否则左边是乱序的
                if (target >= nums[middle] && target <= nums[right]) {
                    // target in [middle, right)  说明target在右边部分中
                    left = middle + 1;
                } else {
                    right = middle - 1;
                }
            }
        }
        return -1;
    }


    public static void main(String[] args) {
        final Solution solution = new Solution();
//        System.out.println(solution.search(new int[]{4, 5, 6, 7, 0, 1, 2}, 0));
//        System.out.println(solution.search(new int[]{4, 5, 6, 7, 0, 1, 2}, 3));
//        System.out.println(solution.search(new int[]{4}, 4));
//        System.out.println(solution.search(new int[]{4}, 0));
//        System.out.println(solution.search(new int[]{3, 1}, 0));
//        System.out.println(solution.search(new int[]{3, 1}, 3));
//        System.out.println(solution.search(new int[]{1, 3}, 3));
        System.out.println(solution.search(new int[]{3, 1}, 1));
    }
}
