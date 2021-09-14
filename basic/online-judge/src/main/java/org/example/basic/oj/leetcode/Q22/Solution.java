package org.example.basic.oj.leetcode.Q22;

import java.util.ArrayList;
import java.util.List;

/**
 * @author anyuan
 * @since 2021-09-14 21:30
 */
class Solution {
    /**
     * 生成有效的括号字符串
     * n是括号对数
     * <p>
     * 1 <= n <= 8
     *
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        List<String> answer = new ArrayList<>();
        process(answer, new char[n * 2], 0, 0);
        return answer;
    }

    /**
     * 相当于每个位置选择左或右，共2^n种选项。但要剪掉无效解，即对于所有位置，剩余未配对的左括号必须为0。
     *
     * @param answer
     * @param currentAnswer
     * @param currentIndex     当前该填的索引位置
     * @param leftBracketCount 当前剩余的未配对左括号数量
     */
    private void process(List<String> answer, char[] currentAnswer, int currentIndex, int leftBracketCount) {
        if (currentIndex == currentAnswer.length) {
            // 已经到最后一位了
            if (leftBracketCount == 0) {
                // 如果刚好为0，那说明这是一个有效解
                answer.add(new String(currentAnswer));
                // 如果这时候还有没配对的左括号，那一定不对了
            }
            return;
        }
        // 一个由n对括号组成的字符串（2n长度），最多只能有n个左括号
        if (leftBracketCount > currentAnswer.length / 2) {
            return;
        }
        // 试着加左括号
        currentAnswer[currentIndex] = '(';
        process(answer, currentAnswer, currentIndex + 1, leftBracketCount + 1);
        // 如果这时候未配对的左括号已经没了（所有左括号都配对了，那这时候就不能加右括号了，加了一定就不是有效字符串了）
        if (leftBracketCount == 0) {
            return;
        }
        // 试着加右括号
        currentAnswer[currentIndex] = ')';
        process(answer, currentAnswer, currentIndex + 1, leftBracketCount - 1);
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.generateParenthesis(3));
        System.out.println(solution.generateParenthesis(1));
        System.out.println(solution.generateParenthesis(8));
    }
}
