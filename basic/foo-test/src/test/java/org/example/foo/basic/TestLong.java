package org.example.foo.basic;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * @author anyuan
 * @since 2021-06-18 11:24
 */
public class TestLong {

    public static void main(String[] args) {
//        double d = Double.valueOf("2.809856E+8");
        double d = Double.valueOf("2.3218941789123789");
        System.out.println(d);
        System.out.println(String.valueOf(BigDecimal.valueOf(d)));
        System.out.println(new DecimalFormat("#.##########").format(d));
        final BigDecimal decimal = new BigDecimal("2.809856E+8");
        System.out.println(decimal.longValue());
        System.out.println(decimal);
    }
}
