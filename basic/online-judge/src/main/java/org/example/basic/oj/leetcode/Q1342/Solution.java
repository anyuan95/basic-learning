package org.example.basic.oj.leetcode.Q1342;

/**
 * @author anyuan
 * @date 2022-01-31 21:19
 */
class Solution {
    /**
     * 14 - 7 - 6 - 3 - 2 - 1 - 0
     * 123 - 122 - 61 - 60 - 30 - 15 - 14 - 7 - 6 - 3 - 2 - 1 - 0
     *
     *
     * @param num
     * @return
     */
    public int numberOfSteps(int num) {
        int count = 0;
        while (num != 0) {
            count += num & 1;
            num >>= 1;
            if (num != 0) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.numberOfSteps(123));
        System.out.println(solution.numberOfSteps(14));
    }
}
