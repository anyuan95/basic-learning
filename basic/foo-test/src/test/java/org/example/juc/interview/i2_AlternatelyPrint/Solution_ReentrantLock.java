package org.example.juc.interview.i2_AlternatelyPrint;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author anyuan
 * @since 2021-05-20 16:16
 */
public class Solution_ReentrantLock {

    public static void main(String[] args) {
        final ReentrantLock reentrantLock = new ReentrantLock();
        final Condition condition = reentrantLock.newCondition();

        Thread t1 = new Thread(() -> {
            reentrantLock.lock();
            for (int i = 'A'; i <= 'Z'; i++) {
                System.out.print((char) i);
                condition.signal();
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            condition.signal();
            reentrantLock.unlock();
        }, "t1");

        Thread t2 = new Thread(() -> {
            reentrantLock.lock();
            for (int i = 1; i <= 26; i++) {
                System.out.print(i);
                condition.signal();
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            condition.signal();
            reentrantLock.unlock();
        }, "t2");

        t1.start();
        t2.start();
    }

}
