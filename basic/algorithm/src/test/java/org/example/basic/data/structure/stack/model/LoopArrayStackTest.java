package org.example.basic.data.structure.stack.model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author anyuan
 * @since 2021-07-31 19:10
 */
public class LoopArrayStackTest {

    @Test
    public void testLoopArrayStack() {
        final LoopArrayStack<Integer> stack = new LoopArrayStack<>(5);
        stack.push(1);
        stack.push(4);
        stack.push(2);
        stack.push(8);
        stack.push(5);
//        stack.push(7);
        System.out.println(stack);
        System.out.println(stack.pop());
        System.out.println(stack);
        System.out.println(stack.pop());
        System.out.println(stack);
        stack.push(0);
        System.out.println(stack);
    }

}