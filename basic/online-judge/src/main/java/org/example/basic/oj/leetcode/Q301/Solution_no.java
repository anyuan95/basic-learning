package org.example.basic.oj.leetcode.Q301;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author anyuan
 * @date 2021-10-27 12:58
 */
class Solution_no {

    public List<String> removeInvalidParentheses(String s) {
        final char[] chars = s.toCharArray();
        final int n = chars.length;
        // 首先计算最终符合条件的字符串的长度、无法匹配的左括号数量、无法匹配的右括号数量
        int left = 0, right = 0;
        for (char aChar : chars) {
            if (aChar == '(') {
                left++;
            } else if (aChar == ')') {
                if (left > 0) {
                    left--;
                } else {
                    right++;
                }
            }
        }
        // 最终得到的结果的长度一定是targetLength
        final int targetLength = n - left - right;
        // 实际上只要删除上面所有left和right个括号就能得到符合题目要求的字符串了
        // 肯定不能随便删，删除之后的左右括号依然需要能对得上
        // 要删除的右括号一定都是在要删除的左括号的左侧的
        // TODO 证明：只要删除的所有右括号都在删除的左括号的左侧，就一定能得到题目要求的符合条件的字符串

        // 右边保留left个左括号的前提下，先在左边尝试删除right个右括号
        // 在每个删除的情况下，再从右侧选择任意位置删除right个右括号
        // 最终的尝试次数大约为 C(right)(n-left) * C(left)(n-right)

        // 相当于是一个回溯
        // 直接把整个原始字符串放到一个stringBuilder中，然后通过递归+回溯方式删除指定位置的字符


        // 先再做一个小处理，先把所有左侧开始连续的右括号 和 右侧开始连续的左括号 删掉
        final StringBuilder builder = new StringBuilder(s);
        int index = 0;
        while (index < builder.length()) {
            if (builder.charAt(index) == '(') {
                break;
            } else if (builder.charAt(index) == ')') {
                builder.deleteCharAt(index);
                right--;
            } else {
                index++;
            }
        }
        index = builder.length() - 1;
        while (index >= 0) {
            if (builder.charAt(index) == ')') {
                break;
            } else if (builder.charAt(index) == '(') {
                builder.deleteCharAt(index);
                // 从左往右删除和从右往左删除不一样，从左往右删除不需要调整游标，但是从右往左需要
                index--;
                left--;
            } else {
                index--;
            }
        }

        HashSet<String> answerSet = new HashSet<>();
        process(answerSet, builder, left, right, 0, builder.length() - 1);

        return new ArrayList<>(answerSet);
    }

    private void process(HashSet<String> set, StringBuilder builder, int left, int right,
                         int leftRemoveIndex, int rightRemoveIndex) {
        if (left == 0 && right == 0) {
            if (isValid(builder)) {
                set.add(builder.toString());
            }
            return;
        }
        if (right > 0) {
            // 如果还有要删除的右括号，就从当前位置开始，找到第一个右括号删掉试试
            // 多减去1，最后一个如果是右括号，也不能删
            while (leftRemoveIndex <= rightRemoveIndex) {
                if (builder.charAt(leftRemoveIndex) == ')') {
                    builder.deleteCharAt(leftRemoveIndex);
                    process(set, builder, left, right - 1, leftRemoveIndex, rightRemoveIndex);
                    builder.insert(leftRemoveIndex, ')');
                }
                leftRemoveIndex++;
            }
        } else if (left > 0) {
            // 同理，如果右括号已经删完了，就开始找左括号删
            while (rightRemoveIndex >= leftRemoveIndex) {
                if (builder.charAt(rightRemoveIndex) == '(') {
                    builder.deleteCharAt(rightRemoveIndex);
                    process(set, builder, left - 1, right, leftRemoveIndex, rightRemoveIndex);
                    builder.insert(rightRemoveIndex, '(');
                }
                rightRemoveIndex--;
            }
        }
    }

    private boolean isValid(StringBuilder builder) {
        int score = 0;
        for (int i = 0; i < builder.length(); i++) {
            if (builder.charAt(i) =='(') {
                score++;
            } else if (builder.charAt(i) == ')') {
                score--;
            }
            if (score < 0) {
                return false;
            }
        }
        return score == 0;
    }

    public static void main(String[] args) {
        final Solution_no solution = new Solution_no();
        System.out.println(solution.removeInvalidParentheses("()())()"));
        System.out.println(solution.removeInvalidParentheses("(a)())()"));
        System.out.println(solution.removeInvalidParentheses("))()(("));
        System.out.println(solution.removeInvalidParentheses(")("));
        System.out.println(solution.removeInvalidParentheses(")(").size());
        System.out.println(solution.removeInvalidParentheses("(("));
        System.out.println(solution.removeInvalidParentheses("((").size());
        System.out.println(solution.removeInvalidParentheses("(((k()(("));
        System.out.println(solution.removeInvalidParentheses("()())()"));
//        "())))()v(k"
    }
}
