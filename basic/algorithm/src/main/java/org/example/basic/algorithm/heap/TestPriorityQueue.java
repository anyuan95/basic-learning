package org.example.basic.algorithm.heap;

import java.util.PriorityQueue;

/**
 * @author anyuan
 * @since 2021-08-04 16:19
 */
public class TestPriorityQueue {

    public static void main(String[] args) {
        // PriorityQueue是jdk中的优先级队列，实际上是堆结构。默认是小根堆。
        final PriorityQueue<Object> queue = new PriorityQueue<>();
        System.out.println(queue.peek());
    }
}
