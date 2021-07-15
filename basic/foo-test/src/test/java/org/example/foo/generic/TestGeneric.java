package org.example.foo.generic;

import java.util.List;

/**
 * @author anyuan
 * @since 2021-01-15 11:25
 */
public class TestGeneric {

    public static void main(String[] args) {
        final List<String> strings = TestGeneric.<List<String>>toObject(null, null);
    }

    public static <T> T toObject(String json, Class<T> clazz) {
        return null;
    }
}
