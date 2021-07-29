package org.example.juc.interview.i2_AlternatelyPrint;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author anyuan
 * @since 2021-05-20 21:59
 */
public class Solution_TwoConditions {

    public static void main(String[] args) {
        final ReentrantLock lock = new ReentrantLock(true);
        final Condition c1 = lock.newCondition();
        final Condition c2 = lock.newCondition();

        final Thread t1 = new Thread(() -> {
            try {
                lock.lock();
                for (int i = 'A'; i <= 'Z'; i++) {
                    System.out.print(((char) i));
                    c2.signal();
                    c1.await();
                }
                c2.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        });
        final Thread t2 = new Thread(() -> {
            try {
                lock.lock();
                for (int i = 1; i <= 26; i++) {
                    c2.await();
                    System.out.print(i);
                    c1.signal();
                }
                c1.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });
        t1.start();
        t2.start();
    }
}
