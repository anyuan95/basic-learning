package org.example.basic.oj.leetcode.Q868;

/**
 * @author anyuan
 * @date 2022-04-24 20:37
 */
class Solution {
    public int binaryGap(int n) {
        int maxGap = 0, index = 0, currentOneIndex = -1;
        // 先找第一个1
        while (n != 0) {
            if ((n & 1) == 1) {
                if (currentOneIndex != -1) {
                    maxGap = Math.max(maxGap, index - currentOneIndex);
                }
                currentOneIndex = index;
            }
            n >>= 1;
            index++;
        }
        return maxGap;
    }
}
