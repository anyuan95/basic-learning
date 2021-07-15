package org.example.basic.loop;

import com.google.common.collect.Lists;

import java.util.ArrayList;

/**
 * @author anyuan
 * @since 2021-05-21 09:08
 */
public class TestFor {

    public static void main(String[] args) {
        final ArrayList<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5);
        for (int i = 0; i < list.size();) {
            System.out.println(list.remove(0));
        }

//        CORRECT
//        for (int size = list.size(); size > 0; size--) {
//            System.out.println(list.remove(0));
//        }
    }
}
