package org.example.basic.oj.leetcode.Q179;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author anyuan
 * @since 2021-08-13 17:55
 */
class Solution {
    public String largestNumber(int[] nums) {
        String answer = Arrays.stream(nums)
                .mapToObj(String::valueOf)
                .sorted((o1, o2) -> (o2 + o1).compareTo(o1 + o2))
                .collect(Collectors.joining());
        while (answer.startsWith("0")) {
            answer = answer.substring(answer.indexOf("0") + 1);
        }
        return answer.isEmpty() ? "0" : answer;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.largestNumber(new int[]{0, 0}));
    }
}
