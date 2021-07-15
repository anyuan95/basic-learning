package org.example.juc.interview.i2_AlternatelyPrint;

import java.util.concurrent.BrokenBarrierException;

/**
 * @author anyuan
 * @since 2021-05-18 21:02
 */
public class Solution_WaitNotify {

    public static void main(String[] args) {

        final Object lock = new Object();
        final Thread t1 = new Thread(() -> {
            synchronized (lock) {
                for (int i = 'A'; i <= 'Z'; i++) {
                    System.out.print(((char) i));
                    lock.notify();
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                lock.notify();
            }
        });
        final Thread t2 = new Thread(() -> {
            synchronized (lock) {
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
        });
        t1.start();
        t2.start();
    }
}
