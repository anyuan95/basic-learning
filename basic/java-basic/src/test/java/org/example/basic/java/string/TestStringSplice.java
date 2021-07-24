package org.example.basic.java.string;

import org.junit.Test;

import java.util.StringJoiner;
import java.util.stream.IntStream;

/**
 * @author anyuan
 * @since 2021-07-16 10:01
 */
public class TestStringSplice {

    @Test
    public void testBuilder() {
        final StringBuilder stringBuilder = new StringBuilder();
        IntStream.range(1, 10).forEach(i -> stringBuilder.append(i).append(","));
        System.out.println(stringBuilder.substring(0, stringBuilder.length() - 1));
    }


    @Test
    public void testJoiner() {
        final StringJoiner stringJoiner = new StringJoiner(",", "[", "]");
        IntStream.range(1, 10).forEach(i -> stringJoiner.add(String.valueOf(i)));
        System.out.println(stringJoiner.toString());

    }
}
