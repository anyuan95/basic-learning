package org.example.juc.interview.i2_AlternatelyPrint;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * 用blockingQueue的阻塞功能
 *
 * @author anyuan
 * @since 2021-05-20 22:31
 */
public class Solution_BlockingQueue {

    public static void main(String[] args) throws InterruptedException {
        final ArrayBlockingQueue<String> queue1 = new ArrayBlockingQueue(1);
        final ArrayBlockingQueue<String> queue2 = new ArrayBlockingQueue(1);

        final Thread t1 = new Thread(() -> {
            for (int i = 'A'; i <= 'Z'; i++) {
                try {
                    System.out.print(((char) i));
                    queue2.put("a");
                    queue1.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        final Thread t2 = new Thread(() -> {
            for (int i = 1; i <= 26; i++) {
                try {
                    queue2.take();
                    System.out.print(i);
                    queue1.put("b");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        t2.start();
    }
}
