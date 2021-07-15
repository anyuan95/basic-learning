package org.example.juc.interview.i1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class TestInterview1_CDL {
    private static class MyCollection<V> {

        volatile List<V> list = Collections.synchronizedList(new ArrayList<>());

        public /*synchronized*/ void add(V object) {
            list.add(object);
        }

        public /*synchronized*/ int size() {
            return list.size();
        }
    }

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        MyCollection<Integer> collection = new MyCollection<>();
        Thread t1 = new Thread(() -> {
            System.out.println("t1 start");
            for (int i = 0; i < 10; i++) {
                System.out.println("add " + i);
                collection.add(i);
                if (collection.size() == 5) {
                    countDownLatch.countDown();
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("t1 end");
        });
        Thread t2 = new Thread(() -> {
            System.out.println("t2 start");
            if (collection.size() != 5) {
                try {
                    countDownLatch.await();
                    System.out.println("it is 5 now!");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("t2 end");
        });
        t2.start();
        t1.start();
    }
}
