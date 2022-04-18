package org.example.basic.oj.leetcode.Q385;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * @author anyuan
 * @date 2022-04-15 22:51
 */
class Solution {
    static NestedInteger tag = new NestedInteger(0);
    public NestedInteger deserialize(String s) {
        final char[] chars = s.toCharArray();
        final Stack<NestedInteger> stack = new Stack<>();
        int index = 0;
        final int n = chars.length;
        while (index < n) {
            if (chars[index] == ',') {
                index++;
            } else if (chars[index] == '-' || chars[index] >= '0' && chars[index] <= '9') {
                boolean positive = true;
                if (chars[index] == '-') {
                    positive = false;
                    index++;
                }
                int number = 0;
                while (index < n && chars[index] >= '0' && chars[index] <= '9') {
                    number *= 10;
                    number += chars[index] - '0';
                    index++;
                }
                if (!positive) {
                    number = -number;
                }
                stack.push(new NestedInteger(number));
            } else if (chars[index] == '[') {
                // 放一个标志位
                stack.push(new NestedInteger());
                stack.push(tag);
                index++;
            } else if (chars[index] == ']') {
                final List<NestedInteger> list = new ArrayList<>();
                while (!stack.isEmpty()) {
                    final NestedInteger pop = stack.pop();
                    if (pop == tag) {
                        break;
                    }
                    list.add(pop);
                }
                for (int i = list.size() - 1; i >= 0; i--) {
                    stack.peek().add(list.get(i));
                }
                index++;
            }
        }
        return stack.peek();
    }

    public static class NestedInteger {
        // Constructor initializes an empty nested list.
        public NestedInteger() {
        }

        // Constructor initializes a single integer.
        public NestedInteger(int value) {
        }

        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger(){return true;}

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger(){return 1;}

        // Set this NestedInteger to hold a single integer.
        public void setInteger(int value){}

        // Set this NestedInteger to hold a nested list and adds a nested integer to it.
        public void add(NestedInteger ni){}

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return empty list if this NestedInteger holds a single integer
        public List<NestedInteger> getList(){return Collections.emptyList();}
    }
}
