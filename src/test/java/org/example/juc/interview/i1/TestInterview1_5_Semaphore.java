package org.example.juc.interview.i1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * @author anyuan
 * @since 2021-05-18 09:49
 */
public class TestInterview1_5_Semaphore {

    private static class MyCollection<V> {

        volatile List<V> list = Collections.synchronizedList(new ArrayList<>());

        public /*synchronized*/ void add(V object) {
            list.add(object);
        }

        public /*synchronized*/ int size() {
            return list.size();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final Semaphore semaphore1 = new Semaphore(1);
        final Semaphore semaphore2 = new Semaphore(1);
        final MyCollection<Integer> collection = new MyCollection<>();

        final Thread t1 = new Thread(() -> {
            System.out.println("t1 start");
            for (int i = 0; i < 10; i++) {
                System.out.println("add " + i);
                collection.add(i);
                if (collection.size() == 5) {
                    try {
                        semaphore1.release();
                        semaphore2.acquire();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("t1 end");
        });

        final Thread t2 = new Thread(() -> {
            System.out.println("t2 start");
            try {
                semaphore1.acquire();
                semaphore2.release();
                System.out.println("it is 5 now");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t2 end");
        });

        semaphore1.acquire();
        semaphore2.acquire();
        t2.start();
        t1.start();
    }
}
