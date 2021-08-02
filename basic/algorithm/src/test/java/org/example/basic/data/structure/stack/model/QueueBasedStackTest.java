package org.example.basic.data.structure.stack.model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author anyuan
 * @since 2021-08-01 15:06
 */
public class QueueBasedStackTest {

    @Test
    public void testQueueBasedStack() {
        final QueueBasedStack<Object> stack = new QueueBasedStack<>();
        stack.push(1);
        System.out.println(stack);
        System.out.println(stack.pop());
        System.out.println(stack);
        stack.push(4);
        stack.push(2);
        stack.push(8);
        stack.push(5);
        stack.push(7);
        System.out.println(stack);
        System.out.println(stack.pop());
        System.out.println(stack);
    }

    @Test
    public void testEmptyQueueBasedStack() {
        final QueueBasedStack<Object> stack = new QueueBasedStack<>();
        System.out.println(stack.pop());
    }
}