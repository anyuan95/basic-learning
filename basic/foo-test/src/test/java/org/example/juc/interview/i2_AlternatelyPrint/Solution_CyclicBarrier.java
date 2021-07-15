package org.example.juc.interview.i2_AlternatelyPrint;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 用两个线程顺序打印字母与数字
 *
 * @author anyuan
 * @since 2021-05-18 14:48
 */
public class Solution_CyclicBarrier {

    public static void main(String[] args) {
//        System.out.println((int) 'A');
//        System.out.println((int) 'Z');

        final CyclicBarrier barrier = new CyclicBarrier(2);

        final Thread t1 = new Thread(() -> {
            for (int i = 'A'; i <= 'Z'; i++) {
                try {
                    barrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.print(((char) i));
            }
        });
        final Thread t2 = new Thread(() -> {
            for (int i = 1; i <= 26; i++) {
                try {
                    barrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.print(i);
            }
        });
        t1.start();
        t2.start();
    }
}
