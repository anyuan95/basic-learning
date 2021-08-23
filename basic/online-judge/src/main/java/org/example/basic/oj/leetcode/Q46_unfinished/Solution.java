package org.example.basic.oj.leetcode.Q46_unfinished;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 全排列
 *
 * @author anyuan
 * @since 2021-08-13 17:13
 */
class Solution {

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> answer = new ArrayList<>();
        process(answer, nums, 0, 0);
        return answer;
    }

    private void process(List<List<Integer>> answer, int[] nums, int currentIndex, int decided) {
        if (decided == nums.length - 1) {
            answer.add(IntStream.of(nums).boxed().collect(Collectors.toList()));
            return;
        }
        for (int i = currentIndex; i < nums.length; i++) {
            swap(nums, currentIndex, i);
            process(answer, nums, i, decided + 1);
            swap(nums, currentIndex, i);
        }
    }

    private void swap(int[] nums, int index1, int index2) {
        if (index1 == index2) return;
        nums[index1] = nums[index1] ^ nums[index2];
        nums[index2] = nums[index1] ^ nums[index2];
        nums[index1] = nums[index1] ^ nums[index2];
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.permute(new int[]{1, 2, 3, 4}));
    }
}
