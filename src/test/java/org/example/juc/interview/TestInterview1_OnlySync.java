package org.example.juc.interview;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 实现一个容器，提供两个方法：add、size
 * 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数，当个数到5时，线程2给出提示并结束
 *
 * @author anyuan
 * @since 2021-05-17 16:23
 */
public class TestInterview1_OnlySync {

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
        final Thread t1 = new Thread(() -> {
//            try {
//                TimeUnit.SECONDS.sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            for (int i = 1; i <= 10; i++) {
                System.out.println("add " + i);
                collection.add(i);
//                System.out.println(collection.list);
//                try {
//                    TimeUnit.SECONDS.sleep(1);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
        });

        final Thread t2 = new Thread(() -> {
            System.out.println("t2 start");
            while (true) {
                if (collection.size() == 5) {
                    System.out.println("size = 5 now !");
//                    t1.stop();
                    break;
                }
            }
        });

        t2.start();
        t1.start();

    }
}
