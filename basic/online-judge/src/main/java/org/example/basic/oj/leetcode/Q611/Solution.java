package org.example.basic.oj.leetcode.Q611;

import java.util.Arrays;

/**
 * 计算数组中有效三角形的个数。
 * <p>
 * 数组长度不超过1000。
 * 数组里整数的范围为 [0, 1000]。
 *
 * @author anyuan
 * @since 2021-08-05 10:39
 */
public class Solution {

    /**
     * 思路1：排序+暴力遍历（优化终止条件）
     * 暴力解法，直接三个指针从左向右移动，第三个指针在发现当前值小于等于前两个指针的和时就跳出。
     * 最差情况，整个数组中所有值相同，时间复杂度ON^3
     *
     * @param nums
     * @return
     */
    public int triangleNumber_ON3(int[] nums) {
        if (nums == null || nums.length < 3) {
            return 0;
        }
        int availableCombinationCount = 0;
        final int length = nums.length;
        Arrays.sort(nums);
        for (int sideAIndex = 0; sideAIndex < length; sideAIndex++) {
            int sideALength = nums[sideAIndex];
            for (int sideBIndex = sideAIndex + 1; sideBIndex < length; sideBIndex++) {
                int sideBLength = nums[sideBIndex];
                for (int sideCIndex = sideBIndex + 1; sideCIndex < length; sideCIndex++) {
                    int sideCLength = nums[sideCIndex];
                    if (sideALength + sideBLength <= sideCLength) {
                        break;
                    } else {
                        availableCombinationCount++;
                    }
                }

            }
        }
        return availableCombinationCount;
    }

    /**
     * 思路3：排序+二分
     * 还是先排序。
     * 取剩余数组中最大数位置i和次大数位置j，则可以认为在[0,j)，找到最小的位置k，满足nums[k]>=nums[i]-nums[j]，
     * 则可以有：对在范围[k,j)范围上的所有位置m，都满足'可以使用m,j,i构成三角形'
     */


    /**
     * 思路2：排序+双指针
     * 减少遍历次数。
     * （还是先排序）
     * 指针1永远指向剩余未处理的数组中最大值，先确定下来。
     * 然后指针2指向指针1前一个位置，指针3指向数组0位置。
     * 如果指针2+指针3>指针1，则由于指针2与指针3中间的值都是在这二者之间，说明这中间的所有值都可以与指针2和指针3组成合理的三角形。
     * 然后指针2与指针3逐渐逼近，如果满足了，就（先累加满足的个数，然后）将指针2向前移；如果不满足，就将指针3向后移。
     *
     *
     * @param nums
     * @return
     */
    public int triangleNumber_ON2(int[] nums) {
        if (nums == null || nums.length < 3) {
            return 0;
        }
        int availableCombinationCount = 0;
        final int length = nums.length;
        Arrays.sort(nums);
        for (int indexA = length - 1; indexA >= 2; indexA--) {
            int indexB = 0;
            int indexC = indexA - 1;
            while (indexB < indexC) {
                if (nums[indexB] + nums[indexC] > nums[indexA]) {
                    availableCombinationCount += indexC - indexB;
                    indexC--;
                } else {
                    indexB++;
                }
            }
        }
        return availableCombinationCount;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        int[] nums = {2, 2, 3, 4};
        System.out.println(solution.triangleNumber_ON2(nums));
    }
}
