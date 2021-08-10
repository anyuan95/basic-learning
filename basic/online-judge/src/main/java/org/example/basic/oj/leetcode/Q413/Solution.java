package org.example.basic.oj.leetcode.Q413;

/**
 * @author anyuan
 * @since 2021-08-10 09:06
 */
class Solution {

    /**
     * 计算给定数组中的等差数列（l>=3）数量。
     * <p>
     * 思路：
     * 1.如果一个数列是等差数列，则其中每两个相邻的数的差都一定相等。
     * 2.由1可得，只要有超过两个连续相等的差，就说明有一个三个数字组成的等差数列。
     * 3.易证得：n个连续相等的差，会生成 n * (n+1) / 2 个等差数列、
     * 4.由2和3可得，遍历相邻差数组，只要发现连续的相同，则记录连续相同次数；
     * 只要发现不同了，就根据之前记录的连续相同次数计算出等差数列个数，进行累加。
     *
     * @param nums
     * @return
     */
    public int numberOfArithmeticSlices(int[] nums) {
        if (nums == null || nums.length < 3) {
            return 0;
        }
        // 保存相邻两数的差
        // diffs[i] = nums[i+1] - nums[i]
        int[] diffs = new int[nums.length - 1];
        for (int i = 1; i < nums.length; i++) {
            diffs[i - 1] = nums[i] - nums[i - 1];
        }
        int arithmeticSequenceCount = 0;
        // 差连续相等
        int diffsContinuouslyEquals = 0;
        for (int i = 1; i < diffs.length; i++) {
            if (diffs[i] == diffs[i - 1]) {
                diffsContinuouslyEquals++;
            } else {
                // n个连续相等的差，会生成 n * (n+1) / 2 个等差数列
                arithmeticSequenceCount += (diffsContinuouslyEquals * (diffsContinuouslyEquals + 1) / 2);
                diffsContinuouslyEquals = 0;
            }
        }
        arithmeticSequenceCount += (diffsContinuouslyEquals * (diffsContinuouslyEquals + 1) / 2);
        return arithmeticSequenceCount;
    }

    /**
     * 思路与上一个方法一致，只是去掉了冗余的相邻差数组。
     *
     * @param nums
     * @return
     */
    public int numberOfArithmeticSlices_NoOnExternalSpace(int[] nums) {
        if (nums == null || nums.length < 3) {
            return 0;
        }
        // 保存相邻两数的差
        // diffs[i] = nums[i+1] - nums[i]
        int arithmeticSequenceCount = 0;
        // 差连续相等
        int diffsContinuouslyEquals = 0;
        for (int i = 2; i < nums.length; i++) {
            if (nums[i] - nums[i-1] == nums[i-1] - nums[i-2]) {
                diffsContinuouslyEquals++;
            } else {
                // n个连续相等的差，会生成 n * (n+1) / 2 个等差数列
                arithmeticSequenceCount += (diffsContinuouslyEquals * (diffsContinuouslyEquals + 1) / 2);
                diffsContinuouslyEquals = 0;
            }
        }
        arithmeticSequenceCount += (diffsContinuouslyEquals * (diffsContinuouslyEquals + 1) / 2);
        return arithmeticSequenceCount;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.numberOfArithmeticSlices_NoOnExternalSpace(new int[]{1, 2, 3, 4}));
        System.out.println(solution.numberOfArithmeticSlices_NoOnExternalSpace(new int[]{1, 2, 3, 4, 5}));
        System.out.println(solution.numberOfArithmeticSlices_NoOnExternalSpace(new int[]{1, 2, 3, 4, 7, 9, 11}));
        System.out.println(solution.numberOfArithmeticSlices_NoOnExternalSpace(new int[]{1, 2, 4}));
    }

}
