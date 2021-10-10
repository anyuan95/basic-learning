package org.example.basic.oj.leetcode.Q187;

import org.checkerframework.checker.units.qual.C;

import java.util.*;

/**
 * @author anyuan
 * @since 2021-10-08 11:52
 */
class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        if (s.length() <= 10) {
            return new ArrayList<>();
        }
        List<String> answer = new ArrayList<>();
        // value=true表示添加过了
        HashMap<Long, Boolean> set = new HashMap<>();
        long hash = 0;
        final char[] chars = s.toCharArray();
        for (int i = 0; i < 10; i++) {
            hash = hash * 10 + (chars[i] - 'A');
        }
        set.put(hash, false);
        for (int i = 10; i < chars.length; i++) {
            hash = (hash - (chars[i - 10] - 'A') * 1000000000L) * 10 + (chars[i] - 'A');
            // 没有这个key，就置为false
            // 如果已经有且是false，那就加到结果列表里并设置为true
            if (!set.containsKey(hash)) {
                set.put(hash, false);
            } else if (!set.get(hash)) {
                answer.add(new String(chars, i - 9, 10));
                set.put(hash, true);
            }
        }
        return answer;
    }

    private final static Map<Character, Integer> CHARACTER_MAP = new HashMap<Character, Integer>() {{
        put('A', 0);
        put('C', 1);
        put('G', 2);
        put('T', 3);
    }};

    /**
     * 不理解为什么这个用二进制位的运算比上边那个直接用十进制进行的运算还要慢
     *
     * @param s
     * @return
     */
    public List<String> findRepeatedDnaSequences_better(String s) {
        if (s.length() <= 10) {
            return new ArrayList<>();
        }
        List<String> answer = new ArrayList<>();
        // value=true表示添加过了
        final int X = 0b11111111111111111111;
        Boolean[] visited = new Boolean[X];
        // 长度是10，每个char用2个二进制位表示
        final char[] chars = s.toCharArray();
        int hash = 0;
        for (int i = 0; i < 10; i++) {
            hash = (hash << 2) | CHARACTER_MAP.get(chars[i]);
        }
        visited[hash] = false;
        for (int i = 10; i < chars.length; i++) {
            // 先加上当前2位，然后清掉超过20位的部分
            hash = ((hash << 2) | CHARACTER_MAP.get(chars[i])) & X;
            // visited中记录三种状态：
            // null表示还没有遍历过这种组合
            // false表示遍历过这种组合一次了
            // true表示遍历过这种组合超过一次了
            if (visited[hash] == null) {
                visited[hash] = false;
            } else if (!visited[hash]) {
                answer.add(new String(chars, i - 9, 10));
                visited[hash] = true;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"));
        System.out.println(solution.findRepeatedDnaSequences("AAAAAAAAAAAAA"));
    }

}
