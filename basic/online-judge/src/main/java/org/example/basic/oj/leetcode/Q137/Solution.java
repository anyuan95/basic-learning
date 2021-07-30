package org.example.basic.oj.leetcode.Q137;

/**
 * 给定数组中只有一个值出现1次，其余值都出现3次
 *
 * @author anyuan
 * @since 2021-07-30 09:44
 */
class Solution {

    /**
     * 制作32位数组，每一位分别保存数组中每个值对应二进制位在该位出现的次数。
     * 假设出现1次的值为x，出现3次的值为y和z，则有：
     * 32位数组中，每一位上的值一定是1x+3y+3z
     *
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        int[] bitArray = new int[32];
        int k = 1, m = 3;
        for (int num : nums) {
            for (int i = 0; i < 32; i++) {
                bitArray[i] += num & 1;
                // 原数组不要了，直接在原数组基础上计算
                num >>>= 1;
            }
        }
        int target = 0;
        // 此处bitArray第0-32位分别对应的是target的第32-0位（高位到低位）
        // 且我这里是逐次地，左移一次，补一个1
        // 所以需要注意bitArray的高低位顺序
        // 其实也可以不用一位一位的移动，直接让target逐次和对应的100..00相或即可
        for (int i = 31; i >= 0; i--) {
            // 模为0  说明x的该位是0
            // 模为k  说明x的该位是1
            target <<= 1;
            if (bitArray[i] % m == k) {
                target |= 1;
            }
        }
        return target;
    }

    public static void main(String[] args) {
//        int[] arr = {-2,-2,1,1,4,1,4,4,-4,-2};
        int[] arr = {-2, -2, -2, 2};
        System.out.println(new Solution().singleNumber(arr));
    }

}
