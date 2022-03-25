package org.example.basic.oj.leetcode.Q393;

/**
 * @author anyuan
 * @date 2022-03-13 23:07
 */
class Solution {
    /**
     * 只有可能是一到四个字节，那么：第一个字节可能的情况有:
     * 1->0xxxxxxx
     * 2->110xxxxx
     * 3->1110xxxx
     * 4->11110xxx
     *
     * @param data
     * @return
     */
    public boolean validUtf8(int[] data) {
        final int n = data.length;
        int index = 0;
        while (index < n) {
            final int first = data[index];
            int byteCount = 0;
            if (first >= 0b00000000 && first < 0b10000000) {
                // 1字节
                byteCount = 1;
            } else if (first >= 0b11000000 && first < 0b11100000) {
                byteCount = 2;
            } else if (first >= 0b11100000 && first < 0b11110000) {
                byteCount = 3;
            } else if (first >= 0b11110000 && first < 0b11111000) {
                byteCount = 4;
            } else {
                return false;
            }
            for (int i = 1; i < byteCount; i++) {
                if (index + i >= n) {
                    return false;
                }
                if (data[index + i] < 0b10000000 || data[index + i] >= 0b11000000) {
                    return false;
                }
            }
            index += byteCount;
        }
        return true;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.validUtf8(new int[]{197, 130, 1}));
    }
}
