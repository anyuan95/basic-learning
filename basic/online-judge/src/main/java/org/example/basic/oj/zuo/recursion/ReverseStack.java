package org.example.basic.oj.zuo.recursion;

import java.util.Stack;

/**
 * 不申请额外空间，使用递归，翻转栈
 *
 * @author anyuan
 * @since 2021-08-17 14:56
 */
public class ReverseStack {

    /**
     * 函数总体功能：翻转栈（中剩余的部分）
     * <p>
     * 每次通过getBottom取到栈底的一个元素，
     * 然后继续调用reverse，
     * 最后将第一步取出的栈底元素压入栈中。
     *
     * @param stack
     */
    public void reverse(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }
        final int bottom = getBottom(stack);
        reverse(stack);
        stack.push(bottom);
    }

    /**
     * 获取栈底元素
     * 使用的还是每次恢复现场的思路。
     *
     * @param stack
     */
    private int getBottom(Stack<Integer> stack) {
        Integer top = stack.pop();
        if (stack.isEmpty()) {
            return top;
        } else {
            int bottom = getBottom(stack);
            stack.push(top);
            return bottom;
        }
    }

    public static void main(String[] args) {
        final ReverseStack reverseStack = new ReverseStack();
        final Stack<Integer> stack = new Stack<>();
        stack.push(5);
        stack.push(4);
        stack.push(3);
        stack.push(2);
        stack.push(1);
        System.out.println(stack);
        reverseStack.reverse(stack);
        System.out.println(stack);
    }
}
