package org.example.basic.oj.leetcode.Q456;

import java.util.Stack;

/**
 * 同时满足：i < j < k 和 nums[i] < nums[k] < nums[j]
 *
 * @author anyuan
 * @since 2021-08-09 10:29
 */
class Solution {
    /**
     * 思路：
     * 先确定i的位置，然后从右向左找到第一个大于i的位置，记为k。然后在i和k之间查找一个大于k的位置。
     *
     * @param nums
     * @return
     */
    public boolean find132pattern(int[] nums) {
        if (nums == null || nums.length < 3) {
            return false;
        }
        final int length = nums.length;
        for (int i = 0; i < length; i++) {
            // 从后向前找，确定k的位置
            for (int k = length - 1; k > i; k--) {
                if (nums[i] < nums[k]) {
                    for (int j = i + 1; j < k; j++) {
                        if (nums[j] > nums[k]) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * 单调栈
     *
     * 思路：
     * 从后向前遍历nums创建一个单调递减的栈，将创建单调栈过程中弹出的值的最大值记录（称为Nk），
     * 如果nums中当前遍历到的位置的值小于Nk，则返回true。
     *
     * 证明：
     * - 由于Nk是被弹出来的，所以栈中一定有比Nk大的值（这个值可以记为Nj）
     * - 由于当前nums中遍历到的位置的值（这个值记作Ni）小于Nk，所以一定可以满足题目要求：i<j<k && Ni<Nj<Nk
     *
     * @param nums
     * @return
     */
    public boolean find132pattern_DecStack(int[] nums) {
        // 单调递减栈
        Stack<Integer> stack = new Stack<>();
        int kValue = Integer.MIN_VALUE;

        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] < kValue) {
                return true;
            }
            while (!stack.isEmpty() && nums[i] > stack.peek()) {
                // 栈顶元素比当前元素小，则把小的元素全弹出去，然后再把当前位置的元素压进去
                kValue = Math.max(stack.pop(), kValue);
            }
            // 把当前位置的值压进栈中
            stack.push(nums[i]);
        }
        return false;
    }


    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.find132pattern_DecStack(new int[]{1, 2, 3, 4}));
        System.out.println(solution.find132pattern_DecStack(new int[]{3, 1, 4, 2}));
        System.out.println(solution.find132pattern_DecStack(new int[]{-1, 3, 2, 0}));
        System.out.println(solution.find132pattern_DecStack(new int[]{1, 3, 2}));
        System.out.println(solution.find132pattern_DecStack(new int[]{2, 1, 3, 2}));
        System.out.println(solution.find132pattern_DecStack(new int[]{1, 3, 2, 3}));
        System.out.println(solution.find132pattern_DecStack(new int[]{2, 1, 3, 2, 3}));
    }

}
