package org.example.basic.oj.leetcode.Q704;

/**
 * 二分查找
 * <p>
 * 给定一个n元素的升序整型数组和要查找的target
 * <p>
 * 要求返回数组下标，不存在则返回-1
 *
 * @author anyuan
 * @since 2021-08-12 17:30
 */
class Solution {
    /**
     * 普通的二分查找
     * <p>
     * 注意边界条件！
     * 注意二分位置！！
     * 注意位运算与加法的优先级问题！！！
     *
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int leftPointer = 0, rightPointer = nums.length - 1;

        while (leftPointer <= rightPointer) {
            int middleIndex = leftPointer + ((rightPointer - leftPointer) >> 1);
            if (nums[middleIndex] == target) {
                return middleIndex;
            } else if (nums[middleIndex] > target) {
                rightPointer = middleIndex - 1;
            } else {
                leftPointer = middleIndex + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
//        System.out.println(solution.search(new int[]{-1}, -1));
//        System.out.println(solution.search(new int[]{-1, 0}, 0));
//        System.out.println(solution.search(new int[]{-1, 0, 3}, 3));
//        System.out.println(solution.search(new int[]{-1, 0, 3, 5, 9, 12}, 3));
        System.out.println(solution.search(new int[]{-1, 0, 3, 5, 9, 12}, 2));
    }
}
