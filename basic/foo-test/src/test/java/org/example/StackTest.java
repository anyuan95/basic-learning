package org.example;

import java.util.Stack;

/**
 * @author anyuan
 * @date 2020-12-02 16:37:02
 */
public class StackTest {

    public static void main(String[] args) {
        final Stack stack = new Stack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(2);
        stack.push(6);
        System.out.println(stack.firstElement());
        System.out.println(stack.search(2));
        System.out.println(stack.lastElement());
    }
}
