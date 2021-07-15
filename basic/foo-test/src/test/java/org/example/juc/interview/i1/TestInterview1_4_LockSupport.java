package org.example.juc.interview.i1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.LockSupport;

/**
 * @author anyuan
 * @since 2021-05-18 09:11
 */
public class TestInterview1_4_LockSupport {

    private static class MyCollection<V> {

        volatile List<V> list = Collections.synchronizedList(new ArrayList<>());

        public /*synchronized*/ void add(V object) {
            list.add(object);
        }

        public /*synchronized*/ int size() {
            return list.size();
        }
    }

    /* TODO 为什么static了就不用final了 */
    static Thread t1 = null, t2 = null;

    public static void main(String[] args) {
        final MyCollection<Integer> collection = new MyCollection<>();

        t1 = new Thread(() -> {
            System.out.println("t1 start");
            for (int i = 0; i < 10; i++) {
                System.out.println("add " + i);
                collection.add(i);
                if (collection.size() == 5) {
                    LockSupport.unpark(t2);
                    LockSupport.park();
                }
            }
            System.out.println("t1 end");
        });

        t2 = new Thread(() -> {
            System.out.println("t2 start");
            if (collection.size() != 5) {
                LockSupport.park();
            }
            System.out.println("t2 end");
            LockSupport.unpark(t1);
        });

        t2.start();
        t1.start();
    }
}
