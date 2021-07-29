package org.example.juc.deadlock;

import org.example.juc.SleepHelper;

/**
 * @author anyuan
 * @since 2021-06-05 15:58
 */
public class TestDeadlock {

    public synchronized static void m1() {

    }

    public synchronized static void m2() {

    }

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            while (true) {
                m1();
                m2();
            }
        }, "t1");
        Thread t2 = new Thread(() -> {
            while (true) {
                m2();
                m1();
            }
        }, "t2");

        t1.start();
        t2.start();

    }
}
