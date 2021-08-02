package org.example.basic.java.primitive;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author anyuan
 * @since 2021-07-27 16:40
 */
public class TestInteger {

    @Test
    public void testEquals() {
        int i1000_1 = 1000;
        int i1000_2 = 1000;
//        System.out.println(i1000_1 == i1000_2);
        Integer I1000_1 = 1000;
        Integer I1000_2 = 1000;
        System.out.println(I1000_1 == I1000_2);
        System.out.println(i1000_1 == I1000_1);
        System.out.println(i1000_1 == I1000_2);
    }

    @Test
    public void testFloat() {
//        int a = (int) 0.5;
        int a = (int) 0.9999;
        System.out.println(a);
    }
}