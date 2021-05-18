package org.example.juc.interview.i3_SyncCollection;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 写一个固定容量同步容器，提供put、get、getCount方法
 * 能够支持2个生产者线程以及10个消费者线程的 阻塞 调用
 * <p>
 * 使用wait/notify/notifyAll實現
 *
 * @author anyuan
 * @since 2021-05-18 15:03
 */
public class Resolution_WaitAndNotify {

    private static class MyCollection<T> {
        LinkedList<T> list = new LinkedList<>();
        static int MAX = 10;

        public synchronized void put(T t) {
            while (list.size() == MAX) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            list.add(t);
            this.notifyAll();
        }

        public synchronized T get() {
            while (list.size() == 0) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            final T t = list.removeFirst();
            this.notifyAll();
            return t;
        }

        public int getCount() {
            return list.size();
        }
    }

    public static void main(String[] args) {

    }

}
