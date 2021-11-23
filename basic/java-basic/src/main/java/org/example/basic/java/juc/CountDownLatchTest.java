package org.example.basic.java.juc;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.RandomUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author anyuan
 * @date 2021-11-23 17:59
 */
public class CountDownLatchTest {

    public static void main(String[] args) {
        final int size = 2785;
        final CountDownLatch countDownLatch = new CountDownLatch(size);
        final ExecutorService pool = Executors.newFixedThreadPool(10);
//        final List<String> list = new ArrayList<>(size);
        final List<String> list = Collections.synchronizedList(new ArrayList<>(size));
        final long startTime = System.currentTimeMillis();
        System.out.println("----- " + startTime);
        for (int i = 0; i < size; i++) {
            pool.submit(() -> {
                ThreadUtil.safeSleep(RandomUtil.randomInt(0, 5000));
                list.add(RandomUtil.randomString(10));
                countDownLatch.countDown();
            });
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        final long endTime = System.currentTimeMillis();
        System.out.println("----- " + endTime);
        System.out.println("----- " + (endTime - startTime));

        System.out.println(list.size());
        System.out.println(list);
        pool.shutdown();
    }


}
