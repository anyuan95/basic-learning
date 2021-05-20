package org.example.juc.interview.i2_AlternatelyPrint;

import java.util.concurrent.TimeUnit;

/**
 * NO
 *
 * @author anyuan
 * @since 2021-05-20 17:35
 */
public class TwoThreadJoin {

    static Thread t1 = null, t2 = null;
    static boolean T1_STARTED = false;

    public static void main(String[] args) {
        final Object lock = new Object();
        t1 = new Thread(() -> {
            synchronized (lock) {
                for (int i = 'A'; i <= 'Z'; i++) {
                    System.out.print(((char) i));
                    T1_STARTED = true;
                    try {
                        t2.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "t1");

        t2 = new Thread(() -> {
            synchronized (lock) {
                while (!T1_STARTED) {
                    try {
                        TimeUnit.MICROSECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                for (int i = 1; i <= 26; i++) {
                    System.out.print(i);
                    try {
                        t1.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "t2");

        t1.start();
        t2.start();
    }
}
