package org.example.basic.oj.leetcode.Q152;

/**
 * 抄的
 */
class Solution_Others {
    public int maxProduct(int[] nums) {
        int max = Integer.MIN_VALUE, imax = 1, imin = 1;
        for(int i=0; i<nums.length; i++){
            if(nums[i] < 0){
                int tmp = imax;
                imax = imin;
                imin = tmp;
            }
            imax = Math.max(imax*nums[i], nums[i]);
            imin = Math.min(imin*nums[i], nums[i]);

            max = Math.max(max, imax);
        }
        return max;
    }

    public static void main(String[] args) {
//        int[] a = {2,3,-2,4};
        int[] a = {-1, 0, -2};
        System.out.println(new Solution_Others().maxProduct(a));
    }
}