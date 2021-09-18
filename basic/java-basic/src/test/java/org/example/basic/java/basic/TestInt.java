package org.example.basic.java.basic;

import org.junit.jupiter.api.Test;

/**
 * @author anyuan
 * @since 2021-09-18 17:08
 */
public class TestInt {

    @Test
    public void testOverflow() {
        int i = Integer.MAX_VALUE;
        System.out.println(i + 1);
    }

}
