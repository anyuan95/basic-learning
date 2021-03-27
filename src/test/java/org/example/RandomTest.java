package org.example;

import java.util.Random;

/**
 * @author anyuan
 * @since 2021-02-05 17:06
 */
public class RandomTest {

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(new Random().nextLong());
        }
    }
}
