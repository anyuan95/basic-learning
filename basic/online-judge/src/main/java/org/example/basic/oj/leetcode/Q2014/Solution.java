package org.example.basic.oj.leetcode.Q2014;

/**
 * @author anyuan
 * @date 2022-03-04 21:36
 */
class Solution {
    /**
     * 求所有连续子数组的最大值和最小值的差的和
     * - 单个元素的子数组的范围一定是0，可以忽略
     * - 题目要求连续子数组
     *
     * 以三个元素为例，a1,a2,a3
     * 结果就是 (a2-a1)+(a3-a2)+(a3-a1) = 2a3-2a1
     *
     * 四个元素
     * (a2-a1)+(a3-a2)+(a4-a3)+(a3-a1)+(a4-a2)+(a4-a1) = 3a4 - 3a1 + a3 - a2
     *
     * 五个元素
     * 2-1 + 3-2 + 4-3 + 5-4 + 3-1 + 4-2 + 5-3 + 4-1 + 5-2 + 5-1 = 4a5 - 4a1 + 2a4 - 2a2
     *
     * 六个元素
     * 2-1 + 3-2 + 4-3 + 5-4 + 6-5 + 3-1 + 4-2 + 5-3 + 6-4 + 4-1 + 5-2 + 6-3 + 5-1 + 6-2 + 6-1
     * = S5 + 5a6 - a1-a2-a3-a4-a5
     * = 5a6 - 5a1 + 3a5 - 3a2 + a4 - a3
     *
     * 七个元素
     * = S6 + 6a7 - a1-a2-a3-a4-a5-a6
     * = 6a7 - 6a1 + 4a6 - 4a2 + 2a5 - 2a3
     *
     * ...... 想屁呢 人家要连续的，排个屁的序
     *
     *
     *
     *
     * @param nums
     * @return
     */
    public long subArrayRanges(int[] nums) {
        int n = nums.length;
        long ans = 0;
        for (int i = 0; i < n; i++) {
            int min = nums[i], max = nums[i];
            for (int j = i + 1; j < n; j++) {
                min = Math.min(min, nums[j]);
                max = Math.max(max, nums[j]);
                ans += max - min;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.subArrayRanges(new int[]{4, -2, -3, 4, 1}));
        // 4,-2,-3,4,1


    }
}