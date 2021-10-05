package org.example.basic.oj.leetcode.Q39;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author anyuan
 * @since 2021-10-04 19:55
 */
class Solution_1004 {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        int n = Arrays.binarySearch(candidates, target);

        n = n >= 0 ? n : ~n - 1;
        List<List<Integer>> answer = new ArrayList<>();
        process(answer, candidates, new ArrayList<>(), n, 0, target);
        return answer;
    }

    private void process(List<List<Integer>> answer, int[] nums, List<Integer> currentAnswer, int n, int currentIndex, int target) {
        for (int i = currentIndex; i <= n; i++) {
            if (nums[i] > target) {
                return;
            } else if (nums[i] == target) {
                currentAnswer.add(nums[i]);
                answer.add(new ArrayList<>(currentAnswer));
                // 这里要注意记录完之后把这次加进去的删掉
                currentAnswer.remove(currentAnswer.size() - 1);
                return;
            } else {
                currentAnswer.add(nums[i]);
                process(answer, nums, currentAnswer, n, i, target - nums[i]);
                currentAnswer.remove(currentAnswer.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        final Solution_1004 solution = new Solution_1004();
//        System.out.println(solution.combinationSum(new int[]{2, 3, 6, 7}, 7));
//        System.out.println(solution.combinationSum(new int[]{2, 3, 6, 7}, 8));
        System.out.println(solution.combinationSum(new int[]{1}, 1));
    }

}
