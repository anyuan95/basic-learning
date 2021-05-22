package org.example.juc;

import java.util.concurrent.TimeUnit;

/**
 * @author anyuan
 * @since 2021-05-21 11:26
 */
public class SleepHelper {

    public static void sleepSeconds(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
