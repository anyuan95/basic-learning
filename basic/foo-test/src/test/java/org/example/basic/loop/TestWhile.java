package org.example.basic.loop;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author anyuan
 * @since 2021-05-21 09:06
 */
public class TestWhile {

    public static void main(String[] args) {
        final ArrayList<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5, 6);
        while (list.size() > 0) {
            System.out.println(list.remove(0));
        }
    }
}
