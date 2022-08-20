package org.example.basic.oj.leetcode.Q2044;

import java.util.List;

class Solution {
    public int countMaxOrSubsets(int[] nums) {
        int target = 0;
        final int n = nums.length;
        for (int num : nums) {
            target |= num;
        }
        // 然后就排列组合找等于这个值的
        int count = 0;
        final int possibilities = 1 << n;
        for (int i = 1; i < possibilities; i++) {
            int temp = i;
            int digit = 0;
            int currentOr = 0;
            while (temp != 0) {
                if ((temp & 1) != 0) {
                    currentOr |= nums[digit];
                }
                temp >>= 1;
                digit++;
            }
            if (currentOr == target) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
//        System.out.println(solution.countMaxOrSubsets(new int[]{3, 1}));
        System.out.println(solution.countMaxOrSubsets(new int[]{3, 2, 1, 5}));
    }
}
