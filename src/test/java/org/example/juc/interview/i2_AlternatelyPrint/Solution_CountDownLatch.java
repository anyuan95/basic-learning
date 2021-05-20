package org.example.juc.interview.i2_AlternatelyPrint;

import java.util.concurrent.CountDownLatch;

/**
 * @author anyuan
 * @since 2021-05-20 17:31
 */
public class Solution_CountDownLatch {

    static Thread t1 = null, t2 = null;

    public static void main(String[] args) {
        final Object lock = new Object();
        final CountDownLatch countDownLatch = new CountDownLatch(1);

        final Thread t1 = new Thread(() -> {
            synchronized (lock) {
                for (int i = 'A'; i <= 'Z'; i++) {
                    System.out.print((char) i);
                    countDownLatch.countDown();
                    lock.notify();
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
//                countDownLatch.countDown();
                }
                lock.notify();
            }
        }, "t1");

        final Thread t2 = new Thread(() -> {
            synchronized (lock) {
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int i = 1; i <= 26; i++) {
                    System.out.print(i);
                    lock.notify();
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                lock.notify();
            }
        }, "t2");

        t1.start();
        t2.start();
    }
}
