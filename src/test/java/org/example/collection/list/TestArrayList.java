package org.example.collection.list;

import java.util.ArrayList;

/**
 * @author anyuan
 * @since 2021-05-20 11:33
 */
public class TestArrayList {

    public static void main(String[] args) {
        final ArrayList<Integer> list = new ArrayList<>();

        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);

        for (int i = 0; i < list.size(); i++) {
            System.out.println("i:" + i + ", size: " + list.size());
            System.out.println(list.remove(0));
        }
    }
}
