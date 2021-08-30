package org.example.basic.oj.leetcode.Q717;

/**
 * @author anyuan
 * @since 2021-08-30 16:15
 */
class Solution_better {
    public boolean isOneBitCharacter(int[] bits) {
        // 实际上只与最后一位的0前面的连续1个数有关
        // 如果前面是奇数个0，则一定是false；偶数则一定是true
        int n = bits.length;
        if (n == 0) {
            return true;
        }
        if (bits[n - 1] == 1) {
            return false;
        }
        int index = n - 2;
        while (index >= 0 && bits[index] == 1) {
            index--;
        }
        return (n - index) % 2 == 0;
    }
}
