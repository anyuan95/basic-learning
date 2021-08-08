package org.example.basic.oj.leetcode.Q18;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 四数之和
 * <p>
 * 有什么意义吗。。。三数之和再循环一次？
 *
 * @author anyuan
 * @since 2021-08-07 22:18
 */
class Solution {

    public List<List<Integer>> fourSum(int[] nums, int target) {
        if (nums == null || nums.length < 4) {
            return new ArrayList<>();
        }
        final List<List<Integer>> answers = new ArrayList<>();
        Arrays.sort(nums);
        final int length = nums.length;
        for (int index1 = 0; index1 < length - 3; index1++) {
            // 提前结束条件在有负数时不再生效
            if (index1 > 0 && nums[index1] == nums[index1 - 1]) {
                continue;
            }
            for (int index2 = index1 + 1; index2 < length - 2; index2++) {
                if (index2 > index1 + 1 && nums[index2] == nums[index2 - 1]) {
                    continue;
                }
                int needNum3AndNum4 = target - nums[index1] - nums[index2];
                int index3 = index2 + 1, index4 = length - 1;
                while (index3 < index4) {
                    int num3AndNum4 = nums[index3] + nums[index4];
                    if (num3AndNum4 == needNum3AndNum4) {
                        final int currentNum3 = nums[index3];
                        final int currentNum4 = nums[index4];
                        // 相等，进行记录
                        // 不知道原因，但是如果使用Arrays.asList比new ArrayList要节省0.5M
                        answers.add(Arrays.asList(nums[index1], nums[index2], currentNum3, currentNum4));

                        // 继续查找2后方等于2的，和3前方等于3的，避免出现重复元组
                        while (index3 < index4 && nums[index3] == currentNum3) {
                            index3++;
                        }
                        while (index3 > index4 && nums[index4] == currentNum4) {
                            index4--;
                        }
                    } else if (num3AndNum4 > needNum3AndNum4) {
                        // 2+3超过目标了，3往前移动
                        index4--;
                    } else {
                        // 2+3小于目标，2往后移动
                        index3++;
                    }
                }
            }
        }
        return answers;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
//        int[] nums = {1, 0, -1, 0, -2, 2};
//        int[] nums = {2,2,2,2,2};
        int[] nums = {1,-2,-5,-4,-3,3,3,5};
        System.out.println(solution.fourSum(nums, -11));
    }
}
