package org.example.basic.oj.leetcode.Q15;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 三数之和
 * 获取数组中所有三数之和为指定值的集合
 *
 * @author anyuan
 * @since 2021-08-07 21:26
 */
class Solution {

    public List<List<Integer>> threeSum(int[] nums) {
        return threeSum(nums, 0);
    }

    public List<List<Integer>> threeSum(int[] nums, int target) {
        if (nums == null || nums.length < 3) {
            return new ArrayList<>();
        }
        final List<List<Integer>> answers = new ArrayList<>();
        Arrays.sort(nums);
        final int length = nums.length;
        for (int index1 = 0; index1 < length - 2; index1++) {
            int num1 = nums[index1];
            // 补充提前结束条件：如果指针1的值都比目标值大，那么后面的值肯定都不满足
            // 实际上只有在target为非负数时才生效，如果target<0则会有问题
            if (num1 > target) {
                return answers;
            }
            if (index1 > 0 && nums[index1] == nums[index1 - 1]) {
                continue;
            }
            int needNum2AndNum3 = target - num1;
            int index2 = index1 + 1, index3 = length - 1;
            while (index2 < index3) {
                int num2AndNum3 = nums[index2] + nums[index3];
                if (num2AndNum3 == needNum2AndNum3) {
                    final int currentNum2 = nums[index2];
                    final int currentNum3 = nums[index3];
                    // 相等，进行记录
                    // 不知道原因，但是如果使用Arrays.asList比new ArrayList要节省0.5M
                    answers.add(Arrays.asList(num1, currentNum2, currentNum3));

                    // 继续查找2后方等于2的，和3前方等于3的，避免出现重复元组
                    while (index2 < index3 && nums[index2] == currentNum2) {
                        index2++;
                    }
                    while (index2 > index3 && nums[index3] == currentNum3) {
                        index3--;
                    }
                } else if (num2AndNum3 > needNum2AndNum3) {
                    // 2+3超过目标了，3往前移动
                    index3--;
                } else {
                    // 2+3小于目标，2往后移动
                    index2++;
                }
            }
        }
        return answers;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
//        int[] nums = {-4, -2, -2, -2, 0, 1, 2, 2, 2, 3, 3, 4, 4, 6, 6};
//        int[] nums = {-1, 0, 1, 2, -1, -4};
        int[] nums = {1,0,0,0};
        System.out.println(solution.threeSum(nums));
    }

}
