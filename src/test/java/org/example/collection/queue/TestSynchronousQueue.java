package org.example.collection.queue;

import java.util.concurrent.SynchronousQueue;

/**
 * @author anyuan
 * @since 2021-05-22 15:09
 */
public class TestSynchronousQueue {

    public static void main(String[] args) throws InterruptedException {
        final SynchronousQueue<Object> queue = new SynchronousQueue<>();
        queue.take();
        System.out.println("over");
    }
}
