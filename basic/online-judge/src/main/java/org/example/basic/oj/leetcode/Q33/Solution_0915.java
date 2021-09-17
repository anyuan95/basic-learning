package org.example.basic.oj.leetcode.Q33;

/**
 * @author anyuan
 * @since 2021-09-15 09:58
 */
class Solution_0915 {
    /**
     * 数组从某一个位置开始旋转了，即从k~n-1的部分被挪到了0前面
     *
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        int n = nums.length, left = 0, right = n - 1, middle;
        while (left <= right) {
            middle = left + ((right - left) >> 1);
            if (nums[middle] == target) {
                return middle;
            }
            // 先确定当前是否位于有序区：由于左侧值一定小于middle，所以如果右侧大于middle则说明是有序，否则无序
            if (nums[middle] >= nums[left]) {
                // 说明当前是在有序区
                // target in [left, middle]
                if (target <= nums[middle] && target >= nums[left]) {
                    // 往左找
                    right = middle - 1;
                } else {
                    left = middle + 1;
                }
            } else {
                // target in [middle, right]
                if (target >= nums[middle] && target <= nums[right]) {
                    left = middle + 1;
                } else {
                    right = middle - 1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        final Solution_0915 solution = new Solution_0915();
        System.out.println(solution.search(new int[]{4, 5, 6, 7, 8, 1, 2, 3}, 8));
    }
}
