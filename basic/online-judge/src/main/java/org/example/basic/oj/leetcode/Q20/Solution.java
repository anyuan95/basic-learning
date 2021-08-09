package org.example.basic.oj.leetcode.Q20;

import java.util.Stack;

/**
 * '('，')'，'{'，'}'，'['，']'
 *
 * @author anyuan
 * @since 2021-08-09 09:20
 */
class Solution {

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        final char[] chars = s.toCharArray();
        for (char aChar : chars) {
            if (aChar == ')' || aChar == '}' || aChar == ']') {
                if (stack.isEmpty()
                        || (aChar == ')' && stack.pop() != '(')
                        || (aChar == '}' && stack.pop() != '{')
                        || (aChar == ']' && stack.pop() != '[')) {
                    return false;
                }
            } else {
                stack.push(aChar);
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.isValid(")"));
        System.out.println(solution.isValid("()"));
        System.out.println(solution.isValid("()[]{}"));
        System.out.println(solution.isValid("(]"));
        System.out.println(solution.isValid("([)]"));
        System.out.println(solution.isValid("{[]}"));
    }
}
