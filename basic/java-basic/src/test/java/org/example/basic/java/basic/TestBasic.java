package org.example.basic.java.basic;

import org.junit.Test;

/**
 * @author anyuan
 * @since 2021-07-22 16:50
 */
public class TestBasic {

    @Test
    public void test1() {
        int a = 10;
        a %= 4;
        System.out.println(a);
        a <<= 2;
        a >>= 2;
    }

    @Test
    public void testPrintBinary() {
        System.out.println(Integer.toBinaryString(200));
    }

    @Test
    public void testOperatorPriority() {
        int a = 5;
        int b = 4;
        int c = a++ - --b * ++a / b-- >> 2 % a--;
        /* -1 ??? */
        System.out.println(c);
    }
}
