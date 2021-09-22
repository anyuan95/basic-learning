package org.example.basic.oj.leetcode.Q15;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author anyuan
 * @since 2021-09-22 15:23
 */
class Solution_0922 {
    public List<List<Integer>> threeSum(int[] nums) {
        return threeSum(nums, 0);
    }

    public List<List<Integer>> threeSum(int[] nums, int target) {
        List<List<Integer>> answer = new ArrayList<>();
        Arrays.sort(nums);

        final int n = nums.length;
        int index1 = 0, index2 = 1, index3 = n - 1;
        // 注意去掉重复值
        // index1的取值范围：由于要给后两个数预留空间(n-1和n-2)，所以是[0,n-3]，即[0,n-2)
        while (index1 < n - 2) {
            // index2的取值范围同理
            index2 = index1 + 1;
            index3 = n - 1;
            while (index2 < index3) {
                // 由于当前数组是升序的，所以如果i2+i3>target-i1，则说明i3应该减小；如果小于则说明i2应该加；如果等于则二者同时往中间靠拢。
                if (nums[index2] + nums[index3] > target - nums[index1]) {
                    index3--;
                } else if (nums[index2] + nums[index3] < target - nums[index1]) {
                    index2++;
                } else { // ==
                    // 记录一个满足匹配的三元组
                    answer.add(Arrays.asList(nums[index1], nums[index2], nums[index3]));

                    // index2找到下一个不同值
                    int currentIndex2Value = nums[index2];
                    while (index2 < index3 && nums[index2] == currentIndex2Value) {
                        index2++;
                    }
                    // index3找到上一个不同值
                    int currentIndex3Value = nums[index3];
                    while (index2 < index3 && nums[index3] == currentIndex3Value) {
                        index3--;
                    }
                }
            }
            // index1找到下一个不重复的值
            int currentIndex1Value = nums[index1];
            while (index1 < n && nums[index1] == currentIndex1Value) {
                index1++;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        final Solution_0922 solution = new Solution_0922();
//        System.out.println(solution.threeSum(new int[]{-1,0,1,2,-1,-4}));
//        System.out.println(solution.threeSum(new int[]{}));
//        System.out.println(solution.threeSum(new int[]{0}));
        System.out.println(solution.threeSum(new int[]{0,0,0}));
    }
}
