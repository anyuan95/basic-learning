package org.example.juc.interview.i1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author anyuan
 * @since 2021-05-17 17:50
 */
public class TestInterview1_2_WaitNotify {

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
        final MyCollection<Integer> collection = new MyCollection<>();
        final Object lock = new Object();

        new Thread(() -> {
            synchronized (lock) {
                System.out.println("t2 start");
                if (collection.size() != 5) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("t2 end");

                /* TODO WARNING 必须加，不然主线程不会停止 */
                lock.notify();
            }
        }, "t2").start();

        new Thread(() -> {
            synchronized (lock) {
                System.out.println("t1 start");
                for (int i = 0; i < 10; i++) {
                    System.out.println("add " + i);
                    collection.add(i);
                    if (i == 5) {
                        lock.notify();
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                System.out.println("t1 end");
            }
        }, "t1").start();
    }
}
