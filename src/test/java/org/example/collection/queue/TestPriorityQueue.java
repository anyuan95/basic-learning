package org.example.collection.queue;

import java.util.PriorityQueue;

/**
 * @author anyuan
 * @since 2021-05-20 11:25
 */
public class TestPriorityQueue {

    public static void main(String[] args) {
        final PriorityQueue<String> queue = new PriorityQueue<>();
        queue.add("a");
        queue.add("b");
        queue.add("c");
        queue.add("d");
        queue.add("e");
        final int size = queue.size();
//        for (int i = 0, realSize = queue.size(); i < size; i++, realSize = queue.size()) {
//            System.out.println(queue.poll());
//            System.out.println(realSize);
//        }

        for (int i = 0; i < queue.size(); i++) {
            System.out.println(queue.poll());
        }
    }
}
