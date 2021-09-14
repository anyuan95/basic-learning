package org.example.basic.oj.leetcode.Q40;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定数组nums和target。
 * 限制：每个元素只能使用一次
 * 要求输出nums中和为target的所有组合（不重复）
 *
 * @author anyuan
 * @since 2021-09-14 09:24
 */
class Solution_backtrack {

    /**
     * 1 <= candidates.length <= 100
     * 1 <= candidates[i] <= 50
     * 1 <= target <= 30
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        // 由于全是正数，所以先进行排序
        Arrays.sort(candidates);
        // 然后把大于target的部分去掉
        // --其实不用去掉，因为已经排序了，而且所有大于rest的内容都会被直接跳过
        List<List<Integer>> answer = new ArrayList<>();
        process(answer, new ArrayList<>(), candidates, 0, target);
        return answer;
    }

    /**
     *
     * @param answer
     * @param currentAnswer
     * @param nums
     * @param currentIndex  可以理解成要往currentAnswer中放的值的位置
     * @param rest
     */
    private void process(List<List<Integer>> answer, List<Integer> currentAnswer, int[] nums, int currentIndex, int rest) {
        if (rest == 0) {
            answer.add(new ArrayList<>(currentAnswer));
            return;
        }
        for (int i = currentIndex; i < nums.length; i++) {
            if (rest < nums[i]) {
                // 由于是有序（升序）的，所以如果出现了一个大于rest的值，再往后遍历只会仍然是更大于
                return;
            }
            // 这个去重条件可以这样理解：每次实际上都是去nums中取一个值，放在currentIndex位置。
            // 发现当前这个位置取的值，和上一次时在当前位置取的值一样，则当前位置的取值跳过这个值
            if (i > currentIndex && nums[i] == nums[i-1]) {
                continue;
            }
            currentAnswer.add(nums[i]);
            process(answer, currentAnswer, nums, i+1, rest - nums[i]);
            currentAnswer.remove(currentAnswer.size() - 1);
        }
    }

    public static void main(String[] args) {
        final Solution_backtrack solution = new Solution_backtrack();
//        System.out.println(solution.combinationSum2(new int[]{2, 3, 6, 7}, 7));
//        System.out.println(solution.combinationSum2(new int[]{2,3,5}, 8));
        System.out.println(solution.combinationSum2(new int[]{10,1,2,7,6,1,5}, 8));
    }
}
