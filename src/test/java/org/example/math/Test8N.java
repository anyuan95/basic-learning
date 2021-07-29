package org.example.math;

import java.util.Random;

/**
 * @author anyuan
 * @since 2021-05-30 16:33
 */
public class Test8N {

    public static void main(String[] args) {
        int b = 8;
        for (int i = 0; i < 10; i++) {
            final int number = new Random().nextInt(1000);
//            final int newNumber = (((number >>> 3) + 1) << 3) + 8;
            final int newNumber = (number + (b - 1)) & ~(b - 1);
            System.out.println("num: " + number + ", new: " + newNumber);
            System.out.println(number / 8  + 1 == newNumber / 8);
        }
    }
}
