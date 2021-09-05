package org.example.basic.oj.leetcode.Q844;

import java.util.Stack;
import java.util.function.Function;

/**
 * @author anyuan
 * @since 2021-09-02 16:45
 */
class Solution {
    public boolean backspaceCompare(String s, String t) {
        Function<String, Stack<Character>> f = (str) -> {
            final Stack<Character> stack = new Stack<>();
            for (char c : str.toCharArray()) {
                if (c == '#') {
                    if (!stack.isEmpty()) {
                        stack.pop();
                    }
                } else {
                    stack.push(c);
                }
            }
            return stack;
        };
        return f.apply(s).equals(f.apply(t));
    }
}
