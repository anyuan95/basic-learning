package org.example.basic.oj.leetcode.Q128;

import java.util.HashSet;
import java.util.Set;

/**
 * @author anyuan
 * @since 2021-08-14 22:13
 */
class Solution2 {
    public int longestConsecutive(int[] nums) {
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }
        int answer = 0;
        for (int num : nums) {
            if (numSet.remove(num)) {
                int currentLength = 1;
                int current = num;
                while (numSet.remove(current - 1)) {
                    current--;
                }
                currentLength += num - current;
                current = num;
                while (numSet.remove(current + 1)) {
                    current++;
                }
                currentLength += current - num;
                answer = Math.max(answer, currentLength);
            }
        }
        return answer;
    }
}
