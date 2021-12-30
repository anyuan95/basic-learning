package org.example.basic.java.basic;

import org.junit.Test;

import java.math.BigDecimal;

/**
 * @author anyuan
 * @date 2021-12-29 18:14
 */
public class TestBigDecimal {
    @Test
    public void testHugeNumberCalculate() {
        final int base = Integer.MAX_VALUE;
        final BigDecimal power = new BigDecimal(base).pow(999999999);
        System.out.println(power);
        System.out.println(String.valueOf(power).length());
    }
}
