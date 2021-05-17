package org.example.juc.latch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author anyuan
 * @since 2021-05-17 10:20
 */
public class TestCountDownLatch {

    public static void main(String[] args) {
        final CountDownLatch countDownLatch = new CountDownLatch(10);

        new Thread(() -> {
            for (int i = 0; i < 30; i++) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "-" + i);
                countDownLatch.countDown();
                System.out.println("latch = " + countDownLatch.getCount());
            }
        }).start();
        new Thread(() -> {
            try {
                countDownLatch.await(5, TimeUnit.SECONDS);
                System.out.println("latch is open now");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

    }
}
