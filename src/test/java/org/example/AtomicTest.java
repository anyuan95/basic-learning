package org.example;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author anyuan
 * @date 2020-12-23 14:32:42
 */
public class AtomicTest {

    static AtomicInteger atomicInteger = new AtomicInteger(0);

    static int i = 0;

    public static void main(String[] args) throws InterruptedException {
        for (int j = 0; j < 100; j++) {
            Thread addThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    // 原子的增加1
                    atomicInteger.incrementAndGet();
                    i++;
                    System.out.println("atomicInteger: " + atomicInteger.get() + ", i:" + i);
                }
            });
            addThread.start();
        }
        Thread.sleep(2000);
        System.out.println(atomicInteger.get());
        System.out.println(i);
    }
}
