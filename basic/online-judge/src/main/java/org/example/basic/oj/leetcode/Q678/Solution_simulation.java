package org.example.basic.oj.leetcode.Q678;

import java.util.Stack;

/**
 * 字符串校验问题，要求左括号和右括号对应，*可以作为任意符号或空
 *
 * @author anyuan
 * @since 2021-09-13 09:52
 */
class Solution_simulation {
    public boolean checkValidString(String s) {
        Stack<Character> stack = new Stack<>();
        char[] chars = s.toCharArray();
        for (char c : chars) {
            if (c == '(' || c == '*') {
                stack.push(c);
            } else if (c == ')') {
                // 这里有一种可能，栈里什么都没有了
                if (stack.isEmpty()) {
                    return false;
                }
                // 把当前位置直到左括号为止都弹出来，然后如果有*再把所有的*压回去
                int count = 0;
                while (!stack.isEmpty() && stack.peek() == '*') {
                    stack.pop();
                    count++;
                }
                // 因为栈里只可能会有(和*，所以直接判断就行了
                if (stack.isEmpty()) {
                    // 如果空了，则用*替换
                    count--;
                } else {
                    // 没空，就拿一个(出来
                    stack.pop();
                }
                // 再把*压回去
                for (int j = 0; j < count; j++) {
                    stack.push('*');
                }
            }
        }
        int count = 0;
        while (!stack.isEmpty()) {
            if (stack.pop() == '*') {
                count++;
            } else {
                if (count == 0) {
                    return false;
                }
                count--;
            }
        }

        return true;
    }
}
