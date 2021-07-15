package org.example.juc.reentrant;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author anyuan
 * @since 2021-05-18 17:44
 */
public class TestReentrantLock {

    public static void main(String[] args) {
        final ReentrantLock reentrantLock = new ReentrantLock();

        final Thread t1 = new Thread(() -> {
            reentrantLock.lock();
            System.out.println("t1 going to sleep");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t1 slept over");
            System.out.println("hold count: " + reentrantLock.getHoldCount());
            reentrantLock.unlock();
        });

        final Thread t2 = new Thread(() -> {
            System.out.println("t2 start");
            reentrantLock.lock();
//            final boolean b = reentrantLock.tryLock();
//            System.out.println("t2 try get lock: " + b);
            System.out.println("t2 finished");
        });

        final Thread t3 = new Thread(() -> {
            System.out.println("t3 start");
            final boolean b = reentrantLock.tryLock();
            System.out.println("t3 get lock: " + b);
            System.out.println("t3 end");
        });

//        t1.start();
//        t2.start();
        t3.start();

    }
}
