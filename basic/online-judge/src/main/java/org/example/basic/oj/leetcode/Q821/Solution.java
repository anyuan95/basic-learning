package org.example.basic.oj.leetcode.Q821;

/**
 * @author anyuan
 * @date 2022-04-19 22:58
 */
class Solution {
    /**
     * 给你一个字符串 s 和一个字符 c ，且 c 是 s 中出现过的字符。
     *
     * 返回一个整数数组 answer ，其中 answer.length == s.length 且 answer[i] 是 s 中从下标 i 到离它 最近 的字符 c 的 距离 。
     *
     *
     * 总结：每个位置的answer实际上就是离该位置最近的两个c的距离之间较小的
     *
     */
    public int[] shortestToChar(String s, char c) {
        final char[] chars = s.toCharArray();
        int minDistance = Integer.MAX_VALUE;
        int index = 0, n = chars.length, lastC = -1;
        final int[] answer = new int[n];
        while (index < n) {
            if (chars[index] == c) {
                if (lastC == -1) {
                    // 第一次设置，把前边所有值都设置一遍
                    for (int i = 0; i < index; i++) {
                        answer[i] = index - i;
                    }
                    answer[index] = 0;
                    lastC = index;
                    index++;
                } else {
                    // 不是第一次设置，那就是说一定有上一个c了
                    for (int i = lastC; i < index; i++) {
                        answer[i] = Math.min(i - lastC, index - i);
                    }
                    answer[index] = 0;
                    lastC = index;
                    index++;
                }
            } else {
                index++;
            }
        }
        // 最后把末尾没填的都填上
        if (lastC < n) {
            for (int i = lastC + 1; i < n; i++) {
                answer[i] = i - lastC;
            }
        }
        return answer;
    }
}
