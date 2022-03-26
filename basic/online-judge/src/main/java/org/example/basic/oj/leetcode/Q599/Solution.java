package org.example.basic.oj.leetcode.Q599;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public String[] findRestaurant(String[] list1, String[] list2) {
        final List<String> answer = new ArrayList<>();
        final Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < list1.length; i++) {
            map.put(list1[i], i);
        }
        int minSum = Integer.MAX_VALUE;
        for (int i = 0; i < list2.length; i++) {
            if (i > minSum) {
                break;
            }
            final String s = list2[i];
            if (map.containsKey(s)) {
                final int temp = map.get(s) + i;
                if (temp == minSum) {
                    answer.add(s);
                } else if (temp < minSum) {
                    answer.clear();
                    answer.add(s);
                    minSum = temp;
                }
            }
        }
        return answer.toArray(new String[0]);
    }
}
