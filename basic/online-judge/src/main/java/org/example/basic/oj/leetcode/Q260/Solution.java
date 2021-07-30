package org.example.basic.oj.leetcode.Q260;

/**
 * 给定一个整数数组 nums，其中恰好有两个元素只出现一次，其余所有元素均出现两次。
 * 找出只出现一次的那两个元素。你可以按 任意顺序 返回答案。
 *
 * @author anyuan
 * @since 2021-07-30 10:27
 */
class Solution {
    public int[] singleNumber(int[] nums) {
        int xor = 0;
        for (int num : nums) {
            xor ^= num;
        }
        // 假定最后要找到的两个值为a和b
        // 最后得到的xor值相当于a^b
        // 从xor二进制位中找出一位值为1的，则说明a和b中有且只有一个值该位为1
        final int lastOne = xor & (~xor + 1);
        int oneResult = xor;
        for (int num : nums) {
            if ((num & lastOne) == lastOne) {
                oneResult ^= num;
            }
        }
        return new int[]{oneResult, xor ^ oneResult};
    }


}
