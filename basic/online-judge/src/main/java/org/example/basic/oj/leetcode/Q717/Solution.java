package org.example.basic.oj.leetcode.Q717;

/**
 * @author anyuan
 * @since 2021-08-30 16:07
 */
class Solution {
    public boolean isOneBitCharacter(int[] bits) {
        if (bits.length == 1) {
            return true;
        }
        final int n = bits.length;
        int index = 0;
        boolean answer = false;
        while (index < n) {
            if (bits[index] == 0) {
                answer |= index == n - 1;
                index++;
            } else if (index + 1 < n) {
                if (bits[index + 1] == 1 || bits[index + 1] == 0) {
                    index += 2;
                }
            }
        }
        return answer;
    }
}
