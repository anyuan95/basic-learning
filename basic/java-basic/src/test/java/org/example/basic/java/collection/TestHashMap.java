package org.example.basic.java.collection;

import org.junit.Test;

import java.util.HashMap;

/**
 * @author anyuan
 * @since 2021-08-05 17:19
 */
public class TestHashMap {

    @Test
    public void testHashMap() {
        final HashMap<String, Integer> map = new HashMap<>();
        map.put("any", 26);
        map.put("llarao", 27);
        map.compute("llarao", (s, integer) -> integer == null ? 27 : integer + 1);
        map.compute("lpd", (s, integer) -> integer == null ? 27 : integer + 1);
        System.out.println(map);

    }
}
