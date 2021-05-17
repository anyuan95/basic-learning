package org.example.juc.thread;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * @author anyuan
 * @since 2021-05-14 17:18
 */
public class TestReentrant {

    synchronized /*static*/ void m1() {
        System.out.println("m1 start");
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        m2();
        System.out.println("m1 end");
    }

    synchronized /*static*/ void m2() {
        System.out.println("m2 start");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("m2 end");
    }

    public static void main(String[] args) {
        System.out.println(LocalDateTime.now());
        new TestReentrant().m1();
        System.out.println(LocalDateTime.now());
    }
}
