package org.example.basic.data.structure.queue.model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author anyuan
 * @since 2021-08-01 14:44
 */
public class StackBasedQueueTest {

    @Test
    public void testStackBasedQueue() {
        final StackBasedQueue<Object> queue = new StackBasedQueue<>();
        queue.add(1);
        queue.add(4);
        queue.add(2);
        queue.add(8);
        queue.add(5);
        queue.add(7);
        System.out.println(queue);
        System.out.println(queue.remove());
        System.out.println(queue);
    }
    @Test
    public void testEmptyStackBasedQueue() {
        final StackBasedQueue<Object> queue = new StackBasedQueue<>();
        System.out.println(queue.remove());
    }

}