package org.example.sync;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

public class TestReentrant {

    synchronized /*static*/ void m1() {
        System.out.println("m1 start");
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
        TestReentrant testReentrant = new TestReentrant();
        new Thread(testReentrant::m1, "m1").start();
        new Thread(testReentrant::m2, "m2").start();
//        testReentrant.m1();
//        testReentrant.m2();
        System.out.println(LocalDateTime.now());
    }
}
