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
     * 这个避免重复当思想是在是太重要了。
     * 这个方法最重要的作用是，可以让同一层级，不出现相同的元素。即
     *                   1
     *                  / \
     *                 2   2  这种情况不会发生 但是却允许了不同层级之间的重复即：
     *                /     \
     *               5       5
     *                 例2
     *                   1
     *                  /
     *                 2      这种情况确是允许的
     *                /
     *               2
     *
     * 为何会有这种神奇的效果呢？
     * 首先 cur-1 == cur 是用于判定当前元素是否和之前元素相同的语句。这个语句就能砍掉例1。
     * 可是问题来了，如果把所有当前与之前一个元素相同的都砍掉，那么例二的情况也会消失。
     * 因为当第二个2出现的时候，他就和前一个2相同了。
     *
     * 那么如何保留例2呢？
     * 那么就用cur > begin 来避免这种情况，你发现例1中的两个2是处在同一个层级上的，
     * 例2的两个2是处在不同层级上的。
     * 在一个for循环中，所有被遍历到的数都是属于一个层级的。我们要让一个层级中，
     * 必须出现且只出现一个2，那么就放过第一个出现重复的2，但不放过后面出现的2。
     * 第一个出现的2的特点就是 cur == begin. 第二个出现的2 特点是cur > begin.
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
