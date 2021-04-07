package org.example.binary;

import java.security.SecureRandom;

/**
 * @author anyuan
 * @since 2021-03-31 11:40
 */
public class BinaryTest {

    public static void main(String[] args) {
//        System.out.println(1 >> 31);
//        System.out.println(-1 >> 31);
//        System.out.println(Integer.MAX_VALUE >> 31);
//        System.out.println(Integer.MIN_VALUE >> 31);

//        System.out.println(~1);
//        System.out.println(~0);
//        System.out.println(~-1);

        System.out.println(Integer.toBinaryString(0));
        System.out.println(Integer.toBinaryString(-1));
//
        int a = Math.abs(new SecureRandom().nextInt() % 1000);
        int b = (a - 128) >> 31;
        int c = ~b & a;
        System.out.println(a);
        System.out.println(Integer.toBinaryString(a));
        System.out.println(b);
        System.out.println(Integer.toBinaryString(b));
        System.out.println(c);
        System.out.println(Integer.toBinaryString(c));
        System.out.println(~c);
        System.out.println(Integer.toBinaryString(~c));
    }
}
