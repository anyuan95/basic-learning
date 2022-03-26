package org.example.basic.oj.leetcode.Q540;

class Solution {
    /**
     * 给你一个仅由整数组成的有序数组，其中每个元素都会出现两次，唯有一个数只会出现一次。
     * 请你找出并返回只出现一次的那个数。
     * 你设计的解决方案必须满足 O(log n) 时间复杂度和 O(1) 空间复杂度。
     *
     *
     * 挺猎奇的，大致思路就是，由于已经限定了只有一个数出现一次，其他数都出现两次，而且是有序的
     * 那就说明了，如果第2k个数不等于第2k+1个数，则那个落单的数一定就在[0.2k]范围内
     *
     * 找到任意一个数，
     * 如果这个数下标是奇数，
     * 0 1 2 3 4
     * - 如果这个数等于上一个数，则说明落单数在[x+1,n-1]
     * - 如果这个数不等于上一个数，则说明落单数在[0,x-1]
     * 如果这个数下标是偶数，
     * 0 1 2 3 4
     * - 如果这个数等于上一个数，则说明落单数在[0,x-2]
     * - 如果这个数不等于上一个数，则说明落单数在[x,n-1]
     * 如果这个数是0
     * 那就看是否和下个数相等
     *
     * @param nums
     * @return
     */
    public int singleNonDuplicate(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int left = 0, right = nums.length-1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (mid % 2 == 0) {
                if (mid > 0) {
                    if (nums[mid] == nums[mid-1]) {
                        right = mid - 2;
                    } else {
                        left = mid;
                    }
                } else /* mid == 0 */{
                    if (nums[0] == nums[1]) {
                        left = 1;
                    } else {
                        return 0;
                    }
                }
            } else {
                if (nums[mid] == nums[mid - 1]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return nums[left];
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.singleNonDuplicate(new int[]{3, 3, 7, 7, 10, 11, 11}));
    }
}
