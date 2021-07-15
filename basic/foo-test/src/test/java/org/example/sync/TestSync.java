package org.example.sync;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class TestSync {

    private Map map;

    public static Integer count = 0;

    public static void m1() throws InterruptedException {
        synchronized (count) {
            System.out.println("m1 s");
            TimeUnit.SECONDS.sleep(5);
            count++;
            System.out.println(count);
            System.out.println("m1 e");
        }
    }

    public static void m2() throws InterruptedException {
        synchronized (TestSync.class) {
            System.out.println("m2 s");
            TimeUnit.SECONDS.sleep(5);
            System.out.println("m2 e");
        }
    }

    public void m3() {
        synchronized (map) {
            System.out.println("do sth");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(LocalDateTime.now());
        new Thread(() -> {
            try {
                m1();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        System.out.println(LocalDateTime.now());
        new Thread(() -> {
            try {
                m2();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        System.out.println(LocalDateTime.now());
    }

}
