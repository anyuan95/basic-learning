package org.example.basic.equal;

import org.junit.Test;

import java.math.BigDecimal;

/**
 * @author anyuan
 * @since 2021-06-12 14:25
 */
public class TestInteger {
    @Test
    public void e0() {
        System.out.println(Integer.MAX_VALUE);
        final BigDecimal bigDecimal = new BigDecimal(2);
        System.out.println(bigDecimal.pow(32));
        System.out.println(Math.pow(2, 32));
    }

    @Test
    public void e1() {
        Integer a1 = 17, a2 = 17;
        Integer b1 = 2017, b2 = 2017;
        Integer c1 = new Integer(17);
        Integer c2 = new Integer(17);
        Integer d1 = new Integer(2017);
        int d2 = 2017;

        System.out.println(a1 == a2);
        System.out.println(b1 == b2);
        System.out.println(c1 == c2);
        System.out.println(d1 == d2);
    }

    @Test
    public void e2() {
        Integer a = 129;
        Integer b = a;
        System.out.println(System.identityHashCode(a));
        a++;
        System.out.println(System.identityHashCode(a));
        System.out.println(a == b);
    }
}
