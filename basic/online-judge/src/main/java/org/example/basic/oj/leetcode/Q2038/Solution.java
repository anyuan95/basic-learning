package org.example.basic.oj.leetcode.Q2038;

class Solution {
    /**
     * 实际上就是找连着的同色的大于3个的
     * 这个数量级就是对应的人最多能走的次数
     *
     * @param colors
     * @return
     */
    public boolean winnerOfGame(String colors) {
        int aTimes = 0, bTimes = 0;
        final char[] chars = colors.toCharArray();
        final int n = chars.length;
        int i = 0;
        while (i < n) {
            int aCount = 0, bCount = 0;
            while (i < n && chars[i] == 'A') {
                aCount++;
                i++;
            }
            if (aCount > 2) {
                aTimes += aCount - 2;
            }

            while (i < n && chars[i] == 'B') {
                bCount++;
                i++;
            }
            if (bCount > 2) {
                bTimes += bCount - 2;
            }
        }
        return aTimes > bTimes;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.winnerOfGame("BBBAAAABB"));
    }
}
