package org.example.basic.oj.leetcode.Q90;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author anyuan
 * @since 2021-10-03 16:53
 */
class Solution_1003 {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> answer = new ArrayList<>();
        Arrays.sort(nums);
        answer.add(new ArrayList<>());
        int lastSize = 0;
        for (int i = 0; i < nums.length; i++) {
            final int size = answer.size();
            int start = 0;
            if (i > 0 && nums[i] == nums[i-1]) {
                start = size - lastSize;
            } else {
                lastSize = size;
            }
            for (int j = start; j < size; j++) {
                final List<Integer> temp = new ArrayList<>(answer.get(j));
                temp.add(nums[i]);
                answer.add(temp);
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        final Solution_1003 solution = new Solution_1003();
//        System.out.println(solution.subsetsWithDup(new int[]{1, 2, 2}));
//        System.out.println(solution.subsetsWithDup(new int[]{1, 2, 2, 2}));
        System.out.println(solution.subsetsWithDup(new int[]{1, 1, 2, 2}));
    }
}
