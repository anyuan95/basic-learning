package org.example;

import java.security.SecureRandom;
import java.util.Random;

/**
 * @author anyuan
 * @since 2021-02-05 17:06
 */
public class RandomTest {

    public static void main(String[] args) {
//        for (int i = 0; i < 10; i++) {
//            System.out.println(new Random().nextLong());
//        }

//        SecureRandom sr = new SecureRandom();
//        int v = Math.abs(sr.nextInt());
//        System.out.println(v);
//        long vl = Math.abs(sr.nextLong());
//        System.out.println(vl);

//        System.out.println(generateLongNumericalUUID(12));
        for (int i = 0; i < 100; i++) {
            System.out.println(generateIntegerNumericalUUID(16));
        }
    }

    public static Long generateLongNumericalUUID(int length) {
        return Math.abs(new SecureRandom().nextLong()) % ((long) Math.pow(10, length));
    }

    public static Integer generateIntegerNumericalUUID(int length) {
        return Math.abs(new SecureRandom().nextInt()) % ((int) Math.pow(10, length));
    }
}
