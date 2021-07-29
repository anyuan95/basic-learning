package org.example.basic.volatile_;

/**
 * @author anyuan
 * @since 2021-05-29 21:38
 */
public class TestVolatile {

    private static volatile int a = 1;

    public static void main(String[] args) {
        a = 2;
        System.out.println(a);
    }
}
