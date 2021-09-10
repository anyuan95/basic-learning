package org.example.basic.oj.leetcode.Q46_need_better;

import java.util.ArrayList;
import java.util.List;

/**
 * 通过boolean数组记录数字使用情况的方式，相对直接在原有数组上进行交换的方式，在效率上有很大的提升。
 * 可以粗略的认为是最优解之一。
 * @author anyuan
 * @since 2021-09-10 10:54
 */
class Solution_bool_used_array {
    /**
     * 全排列
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        final int n = nums.length;
        boolean[] used = new boolean[n];
        List<List<Integer>> answer = new ArrayList<>();
        process(answer, new ArrayList<Integer>(), nums, used, 0);
        return answer;
    }

    /**
     * 改变思路，使用一个used数组进行标记，当前取出了哪个位置的元素，就将该位置标记为true。等本次处理完成后，再将used的该位置改回false。
     * 递归方法中每次遍历数组，找到一个used=false的
     *
     * @param answer
     * @param currentAnswer
     * @param nums
     * @param used
     * @param currentIndex  是当前排列中处理到第几个值了
     */
    private void process(List<List<Integer>> answer, List<Integer> currentAnswer, int[] nums, boolean[] used, int currentIndex) {
        if (currentIndex == nums.length) {
            answer.add(new ArrayList<>(currentAnswer));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            used[i] = true;
            currentAnswer.add(nums[i]);
            process(answer, currentAnswer, nums, used, currentIndex + 1);
            currentAnswer.remove((Object) nums[i]);
            used[i] = false;
        }
    }

    public static void main(String[] args) {
        final Solution_bool_used_array solution = new Solution_bool_used_array();
        System.out.println(solution.permute(new int[]{1, 2, 3}));
        System.out.println(solution.permute(new int[]{1, 2, 3, 4}));
    }
}
