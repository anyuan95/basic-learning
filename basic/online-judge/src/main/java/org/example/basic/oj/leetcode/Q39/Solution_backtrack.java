package org.example.basic.oj.leetcode.Q39;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author anyuan
 * @since 2021-09-13 17:08
 */
class Solution_backtrack {
    /**
     * 回溯方式，实际上也可以理解为DFS方式
     * 关键点在于：
     * 1.for循环：路径选择器
     * 2.if-return判断：终止器
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        // 先剪掉大于target的部分
        Arrays.sort(candidates);
        final int l = Arrays.binarySearch(candidates, target);
        // 这里的操作比较难懂，因为binarySearch如果找不到的话，返回的l取反之后得到的结果，实际上是这个值应该插入的位置
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
        for (int i = currentIndex; i < nums.length; i++) {
            if (rest >= nums[i]) {
                currentAnswer.add(nums[i]);
                process(answer, currentAnswer, nums, i, rest - nums[i]);
                currentAnswer.remove(currentAnswer.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        final Solution_backtrack solution = new Solution_backtrack();
        System.out.println(solution.combinationSum(new int[]{2,3,6,7}, 7));
        System.out.println(solution.combinationSum(new int[]{2,3,5}, 8));
//        System.out.println(solution.combinationSum(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 12));
//        System.out.println(solution.combinationSum(new int[]{1, 2, 3, 4, 5, 7, 8, 9, 10}, 6));
    }
}
