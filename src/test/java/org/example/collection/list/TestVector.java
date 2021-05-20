package org.example.collection.list;

import java.util.Vector;
import java.util.concurrent.TimeUnit;

/**
 * @author anyuan
 * @since 2021-05-19 22:03
 */
public class TestVector {

    public static void main(String[] args) {
        final Vector<Integer> tickets = new Vector<>(1000);
        for (int i = 0; i < 10; i++) tickets.add(i);

        for (int i = 0; i < 4; i++) {
            new Thread(() -> {
                while (tickets.size() > 0) {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(tickets.remove(0));
                }
            }).start();
        }
    }
}
