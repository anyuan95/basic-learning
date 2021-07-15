package org.example.basic.data.structure.stack.simple;

import java.util.Stack;

/**
 * 20. 有效的括号
 * 使用辅助栈做判断
 *
 * @author anyuan
 * @date 2020-12-02 11:43:45
 */
public class ValidBrackets {

    public static void main(String[] args) {
        System.out.println(isValid("()[]{}"));
        System.out.println(isValid("()[]{}"));
    }

    public static boolean isValid(String s) {
        final Stack<Character> stack = new Stack<>();
        char[][] brackets = {{'[', ']'}, {'(', ')'}, {'{', '}'}};
        final char[] chars = s.toCharArray();
        for (char aChar : chars) {
            if (aChar == brackets[0][0] || aChar == brackets[1][0] || aChar == brackets[2][0]) {
                stack.push(aChar);
            } else if (aChar == brackets[0][1] || aChar == brackets[1][1] || aChar == brackets[2][1]) {
                if (stack.empty()) {
                    return false;
                }
                final Character popped = stack.pop();
                if (!isOrdedPair(popped, aChar)) {
                    return false;
                }
            }
        }
        return stack.empty();
    }

    public static boolean isOrdedPair(char c1, char c2) {
        return (c1 == '[' && c2 == ']') || (c1 == '(' && c2 == ')') || (c1 == '{' && c2 == '}');
    }
}
