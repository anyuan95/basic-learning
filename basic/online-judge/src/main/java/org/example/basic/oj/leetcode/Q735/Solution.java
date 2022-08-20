package org.example.basic.oj.leetcode.Q735;

import java.util.Stack;

public class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        final Stack<Integer> stack = new Stack<>();
        for (int asteroid : asteroids) {
            // 记录新元素是否还要保留
            boolean alive = true;
            // 只有栈顶向右，新元素向左，才可能出现碰撞抵消
            while (alive && !stack.isEmpty() && stack.peek() > 0 && asteroid < 0) {
                final Integer peek = stack.peek();
                if (peek >= -asteroid) {
                    alive = false;
                }
                if (peek <= -asteroid) {
                    stack.pop();
                }
            }
            if (alive) {
                stack.push(asteroid);
            }
        }
        return stack.stream().mapToInt(o -> o).toArray();
    }
}
