package org.example.basic.java.basic;

import java.util.Objects;

/**
 * @author anyuan
 * @date 2021-11-26 16:38
 */
public class TestDouble {

    public static void main(String[] args) {
        Double d1 = 0d;
        Double d2 = 0.0d;
        System.out.println(Objects.equals(d1,d2));
        System.out.println(Objects.equals(d1,0));
        System.out.println(Objects.equals(d2,0));

    }
}
