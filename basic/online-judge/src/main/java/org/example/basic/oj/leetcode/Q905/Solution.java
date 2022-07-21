package org.example.basic.oj.leetcode.Q905;

import java.util.ArrayList;
import java.util.List;

/**
 * @author anyuan
 * @date 2022-04-28 23:30
 */
class Solution {
    public int[] sortArrayByParity(int[] nums) {
        final int[] answer = new int[nums.length];
        int evenIndex = 0, oddIndex = nums.length - 1;
        for (int num : nums) {
            if (num % 2 == 0) {
                answer[evenIndex++] = num;
            } else {
                answer[oddIndex--] = num;
            }
        }
        return answer;
    }
}
