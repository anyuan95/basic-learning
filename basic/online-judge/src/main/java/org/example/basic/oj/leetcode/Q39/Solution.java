package org.example.basic.oj.leetcode.Q39;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author anyuan
 * @since 2021-09-13 17:08
 */
class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        // 先剪掉大于target的部分
        Arrays.sort(candidates);
        final int l = Arrays.binarySearch(candidates, target);
//        System.out.println(l < 0 ? ~l : l);
        final int[] range = Arrays.copyOfRange(candidates, 0, l < 0 ? ~l : l+1);
        List<List<Integer>> answer = new ArrayList<>();
        process(answer, new ArrayList<>(), range, 0, target);
        return answer;
    }

    /**
     * @param answer
     * @param currentAnswer
     * @param currentIndex  当前数组位置
     * @param rest          剩余要的和
     */
    private void process(List<List<Integer>> answer, List<Integer> currentAnswer, int[] nums, int currentIndex, int rest) {
        if (rest == 0) {
            answer.add(new ArrayList<>(currentAnswer));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (rest >= nums[i]) {
                currentAnswer.add(nums[i]);
                process(answer, currentAnswer, nums, currentIndex, rest - nums[i]);
                currentAnswer.remove(currentAnswer.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.combinationSum(new int[]{2,3,6,7}, 7));
//        System.out.println(solution.combinationSum(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 12));
//        System.out.println(solution.combinationSum(new int[]{1, 2, 3, 4, 5, 7, 8, 9, 10}, 6));
    }
}
