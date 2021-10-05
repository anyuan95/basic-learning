package org.example.basic.oj.leetcode.Q47;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author anyuan
 * @since 2021-10-03 18:52
 */
class Solution_1003 {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> answer = new ArrayList<>();
        process(answer, nums, 0);
        return answer;
    }

    private void process(List<List<Integer>> answer, int[] nums, int currentIndex) {
        if (currentIndex == nums.length) {
            final List<Integer> temp = new ArrayList<>();
            for (int num : nums) {
                temp.add(num);
            }
            answer.add(temp);
            return;
        }
        for (int i = currentIndex; i < nums.length; i++) {
            if (isRepeated(nums, currentIndex, i)) {
                continue;
            }
            swap(nums, currentIndex, i);
            process(answer, nums, currentIndex+1);
            swap(nums, currentIndex, i);
        }
    }

    private boolean isRepeated(int[] nums, int startIndex, int endIndex) {
        for (int currentIndex = startIndex; currentIndex < endIndex; currentIndex++) {
            if (nums[currentIndex] == nums[endIndex]) {
                return true;
            }
        }
        return false;
    }

    private void swap(int[] nums, int index1, int index2) {
        if (nums[index1] == nums[index2]) {
            return;
        }
        nums[index1] = nums[index1] ^ nums[index2];
        nums[index2] = nums[index1] ^ nums[index2];
        nums[index1] = nums[index1] ^ nums[index2];
    }
}
