package org.example.basic.oj.leetcode.Q335;

/**
 * @author anyuan
 * @since 2021-10-29 23:27
 */
class Solution {
    public boolean isSelfCrossing(int[] distance) {
        final int n = distance.length;
        if (n < 4) {
            return false;
        }
        for (int i = 3; i < n; i++) {
            if (distance[i - 1] <= distance[i - 3] && distance[i] >= distance[i - 2]) {
                return true;
            }
            if (i >= 4 && distance[i - 1] == distance[i - 3] && distance[i] + distance[i - 4] >= distance[i - 2]) {
                return true;
            }
            if (i >= 5 && distance[i - 1] <= distance[i - 3] && distance[i - 2] > distance[i - 4] && distance[i - 1] + distance[i - 5] >= distance[i - 3] && distance[i] + distance[i - 4] >= distance[i - 2]) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.isSelfCrossing(new int[]{2, 1, 1, 2}));
        System.out.println(solution.isSelfCrossing(new int[]{3, 3, 4, 2, 2}));
        System.out.println(solution.isSelfCrossing(new int[]{1, 1, 2, 1, 1}));
        System.out.println(solution.isSelfCrossing(new int[]{1, 1, 1, 1}));
    }
}
