package org.example;

import org.assertj.core.util.Lists;
import org.example.queue.ArrayQueue;

/**
 * @author anyuan
 * @date 2020-12-25 11:22:36
 */
public class QueueTest {

    public static void main(String[] args) {
        ArrayQueue<Integer> queue = new ArrayQueue<>(Lists.newArrayList(1, 2, 3, 4));
        queue.enqueue(5);
        queue.print();
        queue.enqueue(6);
        queue.print();
        queue.enqueue(7);
        queue.print();
        queue.enqueue(8);
        queue.print();
        queue.enqueue(9);
        queue.print();
        queue.enqueue(10);
        queue.print();
        System.out.println(queue.dequeue());
        queue.print();
        System.out.println(queue.dequeue());
        queue.print();
        System.out.println(queue.dequeue());
        queue.print();
        System.out.println(queue.dequeue());
        queue.print();
        System.out.println(queue.dequeue());
        queue.print();
        System.out.println(queue.dequeue());
        queue.print();
        System.out.println(queue.dequeue());
        queue.print();
        System.out.println(queue.dequeue());
        queue.print();
        System.out.println(queue.dequeue());
        queue.print();
        System.out.println(queue.dequeue());
        queue.print();
        System.out.println(queue.dequeue());
        queue.print();
    }

}
