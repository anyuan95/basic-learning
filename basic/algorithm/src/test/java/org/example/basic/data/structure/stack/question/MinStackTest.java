package org.example.basic.data.structure.stack.question;

import org.example.basic.data.structure.stack.model.MinStack;
import org.junit.Test;

/**
 * @author anyuan
 * @since 2021-07-31 18:51
 */
public class MinStackTest {

    @Test
    public void testMinStack() {
        final MinStack<Integer> stack = new MinStack<>(4);
        stack.push(5);
        stack.push(9);
        stack.push(3);
        stack.push(4);
        System.out.println(stack);
        System.out.println(stack.getMin());

    }
}