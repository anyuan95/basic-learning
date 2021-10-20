package org.example.basic.oj.leetcode.Q453;

/**
 * @author anyuan
 * @date 2021-10-20 09:58
 */
class Solution {
    /**
     * 思路：这道题本来是个数学题，思路找到之后列出等式解出来即可
     * 此处用了取巧的方式，题目要求的给n-1个数加一，实际上也就相当于给1个数减一
     * 题目就转变成了：每次给一个数减一，求所有数减到相等所需的最少次数
     *
     * @param nums
     * @return
     */
    public int minMoves(int[] nums) {
        int answer = 0;
        int min = nums[0];
        for (int num : nums) {
            min = Math.min(min, num);
        }
        for (int num : nums) {
            answer += num - min;
        }
        return answer;
    }
}
