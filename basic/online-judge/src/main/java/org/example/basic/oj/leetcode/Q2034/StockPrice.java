package org.example.basic.oj.leetcode.Q2034;

import java.util.*;

class StockPrice {

    int current = 0;
    Map<Integer, Integer> map = new HashMap<>();
    TreeMap<Integer, Integer> tm = new TreeMap<>();

    public StockPrice() {
    }

    public void update(int timestamp, int price) {
        current = Math.max(current, timestamp);
        if (map.containsKey(timestamp)) {
            final Integer old = map.get(timestamp);
            final Integer oldCount = tm.get(old);
            if (oldCount == 1) {
                tm.remove(old);
            } else {
                tm.put(old, oldCount - 1);
            }
        }
        map.put(timestamp, price);
        tm.put(price, tm.getOrDefault(price, 0) + 1);
    }

    public int current() {
        return map.get(current);
    }

    public int maximum() {
        return tm.lastKey();
    }

    public int minimum() {
        return tm.firstKey();
    }

    public static void main(String[] args) {
        final StockPrice stockPrice = new StockPrice();
        stockPrice.update(1,10);
        stockPrice.update(2,5);
        System.out.println(stockPrice.current());
        System.out.println(stockPrice.maximum());
        stockPrice.update(1,3);
        System.out.println(stockPrice.maximum());
        stockPrice.update(4,2);
        System.out.println(stockPrice.minimum());
    }
}
