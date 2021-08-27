package org.example.basic.oj.leetcode.Q15;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 三数之和
 *
 * @author anyuan
 * @since 2021-08-27 16:42
 */
class Solution_0827 {

    /**
     * 思路：1指针不动，2指针和3指针主键逼近，直到相撞，就再找下一个1指针的位置
     * <p>
     * 注意：每次调整完1指针的位置后，需要注意重置2和3指针的位置
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        if (nums.length < 3) {
            return new ArrayList<>();
        }
        int target = 0;
        Arrays.sort(nums);
        final int n = nums.length;
        List<List<Integer>> answer = new ArrayList<>();
        int index1 = 0, index2 = 0, index3 = n - 1;
        // 保持1不动，2和3逐渐逼近
        // 保留2和3的位置
        while (index1 < n - 2) {
            if (nums[index1] > target) {
                return answer;
            }
            index2 = index1 + 1;
            index3 = n - 1;
            int need2And3 = target - nums[index1];
            while (index2 < index3) {
                if (nums[index2] + nums[index3] == need2And3) {
                    answer.add(Arrays.asList(nums[index1], nums[index2], nums[index3]));
                    // 找到了一组满足的值，接下来2和3找下一个不同的值
                    int currentValue2 = nums[index2];
                    while (index2 < index3 && nums[index2] == currentValue2) {
                        index2++;
                    }
                    int currentValue3 = nums[index3];
                    while (index2 < index3 && nums[index3] == currentValue3) {
                        index3--;
                    }
                } else if (nums[index2] + nums[index3] > need2And3) {
                    index3--;
                } else if (nums[index2] + nums[index3] < need2And3) {
                    index2++;
                }
            }
            // 对于当前位置的1，所有可能的2和3找完了，接下来找1的下一个位置
            int currentValue1 = nums[index1];
            while (index1 < n - 2 && nums[index1] == currentValue1) {
                index1++;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        final Solution_0827 solution_0827 = new Solution_0827();
//        System.out.println(solution_0827.threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
        System.out.println(solution_0827.threeSum(new int[]{-1, 0, 1, 2, -1, -4, -2, -3, 3, 0, 4}));
    }
}
