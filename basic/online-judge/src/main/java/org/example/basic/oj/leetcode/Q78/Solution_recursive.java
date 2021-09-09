package org.example.basic.oj.leetcode.Q78;

import java.util.ArrayList;
import java.util.List;

/**
 * @author anyuan
 * @since 2021-09-09 13:54
 */
class Solution_recursive {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> answer = new ArrayList<>();
        process(answer, new ArrayList<Integer>(), nums, 0);
        return answer;
    }

    /**
     * 每个位置的元素有两种决策：要，或不要
     * 由于每个节点都可以有两种子决策，所以可以构造一个二叉树结构:（以1,2为例）
     * EMPTY
     * |       \
     * 1        x
     * |  \     |   \
     * 2   x    2   x
     *
     * 从根节点走到叶子结点的所有路径就是所有结果的情况：[1,2],[1],[2],[x]
     *
     * @param answer
     * @param nums
     */
    private void process(List<List<Integer>> answer, List<Integer> currentAnswer, int[] nums, int currentIndex) {
        if (currentIndex == nums.length) {
            answer.add(new ArrayList<>(currentAnswer));
            return;
        }
        process(answer, currentAnswer, nums, currentIndex + 1);
        currentAnswer.add(nums[currentIndex]);
        process(answer, currentAnswer, nums, currentIndex + 1);
        currentAnswer.remove((Object) nums[currentIndex]);
    }

    public static void main(String[] args) {
        final Solution_recursive solution = new Solution_recursive();
        System.out.println(solution.subsets(new int[]{1, 2, 3}));
    }
}
