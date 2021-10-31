package org.example.basic.oj.leetcode.Q301;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author anyuan
 * @date 2021-10-28 00:27
 */
class Solution {

    public List<String> removeInvalidParentheses(String s) {
        final int n = s.length();
        final char[] chars = s.toCharArray();
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
        int l = 0, r = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '(') {
                l++;
            } else if (s.charAt(i) == ')') {
                r++;
            }
        }
        int maxScore = Math.min(l, r);
        HashSet<String> set = new HashSet<>();
        process(set, s, 0, 0, maxScore, targetLength, "");
        return new ArrayList<>(set);
    }

    private void process(HashSet<String> set, String s, int currentIndex, int currentScore, int maxScore, int targetLength, String currentStr) {
        if (currentScore < 0 || currentScore > maxScore) {
            return;
        }
        if (currentIndex == s.length()) {
            if (currentScore == 0 && currentStr.length() == targetLength) {
                set.add(currentStr);
            }
            return;
        }
        final char c = s.charAt(currentIndex);
        if (c == '(') {
            process(set, s, currentIndex + 1, currentScore, maxScore, targetLength, currentStr);
            process(set, s, currentIndex + 1, currentScore + 1, maxScore, targetLength, currentStr + c);
        } else if (c == ')') {
            process(set, s, currentIndex + 1, currentScore, maxScore, targetLength, currentStr);
            process(set, s, currentIndex + 1, currentScore - 1, maxScore, targetLength, currentStr + c);
        } else {
            process(set, s, currentIndex + 1, currentScore, maxScore, targetLength, currentStr + c);
        }
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
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
