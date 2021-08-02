package org.example.juc.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author anyuan
 * @since 2021-05-22 15:14
 */
public class TestCachedThreadPool {

    static AtomicInteger a = new AtomicInteger(0);

    public static void main(String[] args) {
        final ExecutorService pool = Executors.newCachedThreadPool();
        for (int i = 0; i < 100; i++) {
            pool.submit(() -> {
                System.out.println(a.incrementAndGet());
            });
        }
    }
}
