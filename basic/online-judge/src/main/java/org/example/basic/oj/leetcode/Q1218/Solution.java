package org.example.basic.oj.leetcode.Q1218;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author anyuan
 * @date 2021-11-05 17:53
 */
class Solution {
    /**
     * 求最长等差子序列的长度
     * 题目：给你一个整数数组arr和一个整数difference，请你找出并返回arr中最长等差子序列的长度，该子序列中相邻元素之间的差等于difference 。
     * <p>
     * 子序列 是指在不改变其余元素顺序的情况下，通过删除一些元素或不删除任何元素而从 arr 派生出来的序列。
     *
     * 1 <= arr.length <= 10^5
     * 最大含有10^5的元素数量，所以不能使用n^2的解法
     *
     * @param arr
     * @param difference
     * @return
     */
    public int longestSubsequence(int[] arr, int difference) {
        final int n = arr.length;
        int answer = 1;
        // dp[x]表示从第0位置开始，到x位置为止，能组成的最长等差子序列
        // 这样最终的时间复杂度是n^2
        final int[] dp = new int[n];
        // 一个数只能组成长度为1的最长递增子序列
        Arrays.fill(dp, 1);
        for (int i = 1; i < n; i++) {
            // 实际上这里从前往后找和从后往前找的时间复杂度都是一样的，且二者都是可行的
            // 然后就是从i往前找，看有没有差值等于diff的，如果遇到了等于arr[i]-diff的，就设置值，然后结束
            int targetLastNumber = arr[i] - difference;
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] == targetLastNumber) {
                    dp[i] = dp[j] + 1;
                    answer = Math.max(answer, dp[i]);
                    break;
                }
            }
        }
        return answer;
    }

    /**
     * 用hashmap
     *
     * @param arr
     * @param difference
     * @return
     */
    public int longestSubsequence_hashmap(int[] arr, int difference) {
        final int n = arr.length;
        final HashMap<Integer, Integer> map = new HashMap<>(n);
        int answer = 1;
        for (int num : arr) {
            final int targetNum = num - difference;
            final int currentLS = map.getOrDefault(targetNum, 0) + 1;
            map.put(num, currentLS);
            answer = Math.max(currentLS, answer);
        }
        return answer;
    }

    /**
     * 这种思路实际上不如只用map的
     *
     * 其实就是dp表，加上一个记录了arr[i]的LSS长度的map，然后每遍历到一个arr值时从map里找arr[i]-diff存不存在，存在就计算
     *
     * @param arr
     * @param difference
     * @return
     */
    public int longestSubsequence_dp_hashmap(int[] arr, int difference) {
        return -1;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
//        System.out.println(solution.longestSubsequence(new int[]{1, 2, 3, 4}, 1));
//        System.out.println(solution.longestSubsequence(new int[]{1, 3, 5, 7}, 1));
//        System.out.println(solution.longestSubsequence(new int[]{1, 5, 7, 8, 5, 3, 4, 2, 1}, -2));
//        System.out.println(solution.longestSubsequence(new int[]{1}, -2));
        System.out.println(solution.longestSubsequence_hashmap(new int[]{3,4,-3,-2,-4}, -5));
    }
}
