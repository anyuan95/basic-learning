package org.example.basic.data.structure.queue.model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author anyuan
 * @since 2021-08-01 14:21
 */
public class LoopArrayQueueTest {

    @Test
    public void testLoopArrayQueue() {
        final LoopArrayQueue<Object> queue = new LoopArrayQueue<>(5);
        queue.add(1);
        queue.add(4);
        queue.add(2);
        queue.add(8);
        queue.add(5);
        System.out.println(queue);
        System.out.println(queue.remove());
        System.out.println(queue);
        queue.add(7);
        System.out.println(queue);
        System.out.println(queue.remove());
        System.out.println(queue);
    }

}