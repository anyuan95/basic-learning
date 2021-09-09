package org.example.basic.oj.leetcode.Q47;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author anyuan
 * @since 2021-09-09 22:30
 */
class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> answer = new ArrayList<>();
        process(answer, nums, 0);


        return answer;
    }

    private void process(List<List<Integer>> answer, int[] nums, int currentIndex) {
        if (currentIndex == nums.length) {
            answer.add(Arrays.stream(nums).boxed().collect(Collectors.toList()));
            return;
        }
        for (int i = currentIndex; i < nums.length; i++) {
            if (currentIndex != i && nums[currentIndex] == nums[i]) {
                continue;
            }
            swap(nums, currentIndex, i);
            process(answer, nums, currentIndex + 1);
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
        System.out.println(solution.permuteUnique(new int[]{1, 2, 3, 4}));
        System.out.println(solution.permuteUnique(new int[]{1, 2, 3, 3}));
    }
}
