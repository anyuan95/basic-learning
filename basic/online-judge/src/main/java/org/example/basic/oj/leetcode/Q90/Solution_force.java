package org.example.basic.oj.leetcode.Q90;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 子集2、
 *
 * 在子集1的前提上，要求不能有重复集合（指集合内元素完全相同的两个集合）
 *
 * @author anyuan
 * @since 2021-09-09 15:17
 */
class Solution_force {
    /**
     * 偷懒，先将原数组做排序，就能保证重复集合被清理掉了
     * 性能巨慢
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> answer = new ArrayList<>();
        List<Integer> currentAnswer = new ArrayList<>();
        final int n = nums.length;
        for (int index = 0; index < (1 << n); index++) {
            for (int digit = 0; digit < n; digit++) {
                // 只有左数第digit位是1才选中
                if ((index & (1 << digit)) != 0) {
                    currentAnswer.add(nums[digit]);
                }
            }
            if (!answer.contains(currentAnswer)) {
                answer.add(new ArrayList<>(currentAnswer));
            }
            currentAnswer.clear();
        }
        return answer;
    }

    public static void main(String[] args) {
        final Solution_force solution = new Solution_force();
        System.out.println(solution.subsetsWithDup(new int[]{1, 2, 2}));
        System.out.println(solution.subsetsWithDup(new int[]{4,4,4,1,4}));
    }
}
