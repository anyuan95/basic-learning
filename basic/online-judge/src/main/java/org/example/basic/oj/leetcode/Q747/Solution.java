package org.example.basic.oj.leetcode.Q747;

class Solution {
    public int dominantIndex(int[] nums) {
        int max = -1, secondMax = -2;
        int maxIndex = -1;

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (num > max) {
                max = num;
                if (maxIndex >= 0) {
                    secondMax = nums[maxIndex];
                }
                maxIndex = i;
            } else if (num > secondMax) {
                secondMax = num;
            }
        }
        return max >= secondMax * 2 ? maxIndex : -1;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.dominantIndex(new int[]{3, 6, 1, 0}));
        System.out.println(solution.dominantIndex(new int[]{1,2,3,4}));
        System.out.println(solution.dominantIndex(new int[]{1}));
    }
}
