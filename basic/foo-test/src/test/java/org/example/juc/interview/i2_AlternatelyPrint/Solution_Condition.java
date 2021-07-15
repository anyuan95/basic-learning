package org.example.juc.interview.i2_AlternatelyPrint;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author anyuan
 * @since 2021-05-18 21:20
 */
public class Solution_Condition {

    /**
     * TODO 停不掉的原因？
     *
     * @param args
     */
    public static void main(String[] args) {
        final ReentrantLock lock = new ReentrantLock(true);
        final Condition condition = lock.newCondition();

        final Thread t1 = new Thread(() -> {
            for (int i = 'A'; i <= 'Z'; i++) {
                try {
                    lock.lock();
                    condition.signal();
                    System.out.print(((char) i));
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        });
        final Thread t2 = new Thread(() -> {
            for (int i = 1; i <= 26; i++) {
                try {
                    lock.lock();
                    condition.signal();
                    System.out.print(i);
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        });
        t1.start();
        t2.start();
    }
}
