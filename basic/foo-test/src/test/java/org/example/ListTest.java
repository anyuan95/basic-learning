package org.example;

import org.assertj.core.util.Lists;

import java.util.ArrayList;

/**
 * @author anyuan
 * @since 2021-03-15 16:01
 */
public class ListTest {

    public static void main(String[] args) {
        final ArrayList<Integer> integers = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 0);
        integers.remove(6);

    }
}
