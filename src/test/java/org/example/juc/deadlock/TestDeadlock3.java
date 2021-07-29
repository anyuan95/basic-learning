package org.example.juc.deadlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author anyuan
 * @since 2021-06-05 16:12
 */
public class TestDeadlock3 {

    public static void main(String[] args) {
        final ReentrantLock lock1 = new ReentrantLock();
        final ReentrantLock lock2 = new ReentrantLock();

        final Thread t1 = new Thread(() -> {
            while (true) {
                lock1.lock();
                lock2.lock();
                System.out.println("t1");
                lock2.unlock();
                lock1.unlock();
            }
        });

        final Thread t2 = new Thread(() -> {
            while (true) {
                lock2.lock();
                lock1.lock();
                System.out.println("t1");
                lock1.unlock();
                lock2.unlock();
            }
        });

        t1.start();
        t2.start();
    }
}
