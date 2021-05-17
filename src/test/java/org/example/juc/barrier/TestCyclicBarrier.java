package org.example.juc.barrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author anyuan
 * @since 2021-05-17 10:58
 */
public class TestCyclicBarrier {

    public static void main(String[] args) {
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(10, () -> System.out.println("人齐了，开车"));

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    if (i == 9) {
                        TimeUnit.SECONDS.sleep(10);
                    }
//                    TimeUnit.SECONDS.sleep(1);
                    cyclicBarrier.await(5, TimeUnit.SECONDS);
                    System.out.println("用户" + i + "就位.");
                } catch (InterruptedException | BrokenBarrierException | TimeoutException e) {
                    e.printStackTrace();
                }
            }

        }).start();

    }
}
