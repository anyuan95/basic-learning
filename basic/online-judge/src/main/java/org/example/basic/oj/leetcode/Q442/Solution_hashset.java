package org.example.basic.oj.leetcode.Q442;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author anyuan
 * @date 2021-10-18 15:44
 */
class Solution_hashset {

    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> answer = new ArrayList<>();
        final int n = nums.length;
        final HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            if (set.contains(nums[i])) {
                answer.add(nums[i]);
            } else {
                set.add(nums[i]);
            }
        }
        return answer;
    }
}
