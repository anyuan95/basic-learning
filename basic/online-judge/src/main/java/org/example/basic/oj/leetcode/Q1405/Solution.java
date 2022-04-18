package org.example.basic.oj.leetcode.Q1405;

import java.util.PriorityQueue;

class Solution {
    /**
     * 假如a有a个，b有b个，则当a / (b+1) >= 3时，一定会有aaa
     * 现在把问题数量改为3个字母，三个字母实际上可以按照两种字母的方式思考，即a和非a
     * <p>
     * 想到的方式，尽可能地把两个字母放在一起
     * <p>
     * 答案的思路：每次都优先取剩的最多的字母，除非不满足条件了
     * <p>
     * '最多'有a个a，b个b，c个c
     *
     * @param a
     * @param b
     * @param c
     * @return
     */
    public String longestDiverseString(int a, int b, int c) {
        // 记录字母类型，和剩余的个数
        final PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> o2[1] - o1[1]);
        if (a > 0) {
            queue.add(new int[]{0, a});
        }
        if (b > 0) {
            queue.add(new int[]{1, b});
        }
        if (c > 0) {
            queue.add(new int[]{2, c});
        }

        StringBuilder builder = new StringBuilder();
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            char curChar = (char) (cur[0] + 'a');
            final int len = builder.length();
            if (len >= 2 && builder.charAt(len - 1) == curChar && builder.charAt(len - 2) == curChar) {
                if (queue.isEmpty()) {
                    break;
                }
                // 如果放第一多的元素不符合规则，那就放第二多的
                final int[] next = queue.poll();
                builder.append((char) (next[0] + 'a'));
                if (next[1] != 1) {
                    next[1]--;
                    queue.add(next);
                }
                queue.add(cur);
            } else {
                builder.append((char) (cur[0] + 'a'));
                if (cur[1] != 1) {
                    cur[1]--;
                    queue.add(cur);
                }
            }
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.longestDiverseString(7, 1, 0));
    }
}