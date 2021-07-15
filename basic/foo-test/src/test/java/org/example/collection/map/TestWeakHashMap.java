package org.example.collection.map;

import java.util.WeakHashMap;

/**
 * @author anyuan
 * @since 2021-05-19 11:01
 */
public class TestWeakHashMap {

    public static void main(String[] args) {
        final WeakHashMap<String,String> map = new WeakHashMap<>(16);
        map.put("1", "a");
        map.put("2", "b");
        map.put("3", "c");
        System.out.println(map);
    }
}
