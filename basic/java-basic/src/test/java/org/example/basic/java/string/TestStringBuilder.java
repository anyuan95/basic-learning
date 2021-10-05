package org.example.basic.java.string;

import org.junit.Test;

/**
 * @author anyuan
 * @since 2021-09-09 11:26
 */
public class TestStringBuilder {

    @Test
    public void testNChars() {
        int n = 1000;
        char c = ' ';
        final StringBuilder builder = new StringBuilder();
    }

    @Test
    public void testNull() {
        final StringBuilder builder = null;
        System.out.println(builder);
        System.out.println(new StringBuilder(null));
    }
}
