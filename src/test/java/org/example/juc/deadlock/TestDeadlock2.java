package org.example.juc.deadlock;

/**
 * @author anyuan
 * @since 2021-06-05 16:08
 */
public class TestDeadlock2 {
    public static void main(String[] args) {
        Object lock1 = new Object();
        Object lock2 = new Object();

        final Thread t1 = new Thread(() -> {
            while (true) {
                synchronized (lock1) {
                    synchronized (lock2) {
                        System.out.println("t1");
                    }
                }
            }
        });

        final Thread t2 = new Thread(() -> {
            while (true) {
                synchronized (lock2) {
                    synchronized (lock1) {
                        System.out.println("t2");
                    }
                }
            }
        });

        t1.start();
        t2.start();
    }
}
