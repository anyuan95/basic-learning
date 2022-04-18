package org.example.basic.oj.leetcode.Q553;

 class Solution {
     /**
      * 一个数，直接返回；
      * 两个数，不用括号
      *
      * @param nums
      * @return
      */
     public String optimalDivision(int[] nums) {
         final int n = nums.length;
         if (n == 1) {
             return String.valueOf(nums[0]);
         } else if (n == 2) {
             return nums[0] + "/" + nums[1];
         }
         final StringBuilder builder = new StringBuilder().append(nums[0]).append("/(").append(nums[1]);
         for (int i = 2; i < n; i++) {
             builder.append("/").append(nums[i]);
         }
         builder.append(")");
         return builder.toString();
     }
}
