package org.example.basic.oj.leetcode.Q229;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author anyuan
 * @date 2021-10-22 10:30
 */
class Solution_better {
    /**
     * 摩尔投票法
     * 能够在空间复杂度为O1的前提下，快速统计出现次数超过n/k的所有元素。
     *
     * @param nums
     * @return
     */
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> answer = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return answer;
        }
        int cand1 = nums[0], count1 = 0;
        int cand2 = nums[0], count2 = 0;
        for (int num : nums) {
            if (num == cand1) {
                count1++;
            } else if (num == cand2) {
                count2++;
            } else if (count1 == 0) {
                cand1 = num;
                count1++;
            } else if (count2 == 0) {
                cand2 = num;
                count2++;
            } else {
                count1--;
                count2--;
            }
        }
        count1 = 0;
        count2 = 0;
        for (int num : nums) {
            if (num == cand1) {
                count1++;
            } else if (num == cand2) {
                count2++;
            }
        }
        final int n = nums.length;
        if (count1 > n / 3) {
            answer.add(cand1);
        }
        if (count2 > n / 3) {
            answer.add(cand2);
        }
        return answer;
    }
}
