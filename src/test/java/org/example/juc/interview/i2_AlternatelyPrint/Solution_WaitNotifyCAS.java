package org.example.juc.interview.i2_AlternatelyPrint;

import java.util.concurrent.CountDownLatch;

/**
 * @author anyuan
 * @since 2021-05-20 17:19
 */
public class Solution_WaitNotifyCAS {

    public static volatile boolean T1_STARTED = false;

    public static void main(String[] args) {
//        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final Object lock = new Object();

        final Thread t1 = new Thread(() -> {
            synchronized (lock) {
                for (int i = 'A'; i <= 'Z'; i++) {
                    System.out.print((char) i);
                    T1_STARTED = true;
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
                while (!T1_STARTED) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                for (int i = 1; i <= 26; i++) {
//                try {
//                    countDownLatch.await();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
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
