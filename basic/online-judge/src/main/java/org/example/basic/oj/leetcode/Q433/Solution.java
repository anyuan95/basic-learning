package org.example.basic.oj.leetcode.Q433;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * @author anyuan
 * @date 2022-05-07 23:45
 */
class Solution {
    private static final char[] ITEMS = new char[]{'A', 'C', 'G', 'T'};
    public int minMutation(String start, String end, String[] bank) {
        final Set<String> set = new HashSet<>(Arrays.asList(bank));
        final Map<String, Integer> map = new HashMap<>();
        map.put(start, 0);
        final Queue<String> queue = new LinkedList<>();
        queue.add(start);
        while (!queue.isEmpty()) {
            final String poll = queue.poll();
            final int step = map.get(poll);
            final char[] chars = poll.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                for (char item : ITEMS) {
                    if (chars[i] == item) {
                        continue;
                    }
                    final char[] chars1 = chars.clone();
                    chars1[i] = item;
                    final String str = String.valueOf(chars1);
                    if (!set.contains(str)) continue;
                    if (map.containsKey(str)) continue;
                    if (str.equals(end)) return step + 1;
                    map.put(str, step + 1);
                    queue.add(str);
                }
            }
        }
        return -1;
    }
}
