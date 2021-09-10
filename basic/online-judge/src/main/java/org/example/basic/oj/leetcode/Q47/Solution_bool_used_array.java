package org.example.basic.oj.leetcode.Q47;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author anyuan
 * @since 2021-09-10 16:24
 */
class Solution_bool_used_array {
    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> answer = new ArrayList<>();
        final boolean[] used = new boolean[nums.length];
        process(answer, new ArrayList<Integer>(), nums, used, 0);
        return answer;
    }

    private void process(List<List<Integer>> answer, List<Integer> currentAnswer, int[] nums, boolean[] used, int currentIndex) {
        if (currentIndex == nums.length) {
            answer.add(new ArrayList<>(currentAnswer));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }
            used[i] = true;
            currentAnswer.add(nums[i]);
            process(answer, currentAnswer, nums, used, currentIndex + 1);
            // remove last
            currentAnswer.remove(currentAnswer.size() - 1);
            used[i] = false;
        }
    }

    public static void main(String[] args) {
        final Solution_bool_used_array solution = new Solution_bool_used_array();
        System.out.println(solution.permuteUnique(new int[]{1, 2, 3, 4}));
        System.out.println(solution.permuteUnique(new int[]{1, 2, 3, 3}));
    }
}
