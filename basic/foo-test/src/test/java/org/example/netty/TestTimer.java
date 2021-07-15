package org.example.netty;

import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import io.netty.util.Timer;
import org.example.juc.SleepHelper;
import org.junit.Test;

import java.util.concurrent.TimeUnit;


/**
 * @author anyuan
 * @since 2021-07-07 16:03
 */
public class TestTimer {

    @Test
    public void testMillis() {
        System.out.println(System.currentTimeMillis());
    }

    @Test
    public void testTimerTask() throws InterruptedException {
        HashedWheelTimer timer = new HashedWheelTimer(r -> new Thread(r, "TEST-THREAD-TIMER"));
        final Timeout work = timer.newTimeout(timeout -> {
            long start = System.currentTimeMillis();
            while (true) {
                if (System.currentTimeMillis() - start > 5000) {
                    System.out.println("one print!");
                    start = System.currentTimeMillis();
                }
            }
        }, 1, TimeUnit.SECONDS);

        SleepHelper.sleepSeconds(13);
        work.cancel();
        if (work.isCancelled()) {
            System.out.println("cancelled");
        }



        Thread.sleep(100000L);

    }
}
