package org.example.juc.interview.i2_AlternatelyPrint;

/**
 * 注意：每个线程循环执行结束后必须执行一次notify，否则会有一个线程最终处于wait状态，导致程序无法停止
 * @author anyuan
 * @since 2021-05-20 16:16
 */
public class Solution_WaitNotify {

    static Thread t1 = null, t2 = null;

    public static void main(String[] args) {
        final Object lock = new Object();

        t1 = new Thread(() -> {
            synchronized (lock) {
                for (int i = 'A'; i <= 'Z'; i++) {
                    System.out.print((char)i);
                    lock.notify();
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                lock.notify();
            }
        }, "t1");

        t2 = new Thread(() -> {
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
        }, "t2");
        t1.start();
        t2.start();

    }

}
