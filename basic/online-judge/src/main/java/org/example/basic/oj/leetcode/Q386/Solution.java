package org.example.basic.oj.leetcode.Q386;

import java.util.ArrayList;
import java.util.List;

/**
 * @author anyuan
 * @date 2022-04-18 23:27
 */
class Solution {
    /**
     * 给你一个整数 n ，按字典序返回范围 [1, n] 内所有整数。
     * <p>
     * 你必须设计一个时间复杂度为 O(n) 且使用 O(1) 额外空间的算法。
     */
    public List<Integer> lexicalOrder(int n) {
        final List<Integer> answer = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            process(i, n, answer);
        }
        return answer;
    }

    /**
     * 尝试在当前数末尾添加0-9，如果添加后超过范围，则改为最后一位加一
     * 如果最后一位到9，那么删除直到（包括）9，然后当前位加一
     */
    private void process(int current, int max, List<Integer> answer) {
        if (current > max) {
            return;
        }
        answer.add(current);
        for (int i = 0; i <= 9; i++) {
            process(current * 10 + i, max, answer);
        }
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.lexicalOrder(25));
    }
}
