package org.example.basic.oj.leetcode.Q318;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author anyuan
 * @since 2021-11-17 00:20
 */
class Solution {
    public int maxProduct(String[] words) {
        final int n = words.length;
        int[][] details = new int[n][2];
        for (int i = 0; i < words.length; i++) {
            details[i] = new int[]{turnToInt(words[i]), words[i].length()};
        }
        Arrays.sort(details, (o1, o2) -> o1[1] == o2[1] ? o2[0] - o1[0] : o2[1] - o1[1]);
        int answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if ((details[i][0] & details[j][0]) == 0) {
                    answer = Math.max(answer, details[i][1] * details[j][1]);
                    break;
                }
            }
        }
        return answer;
    }

    /**
     * 对于掩码一样的字符串，只保留最长的一个
     * @param words
     * @return
     */
    public int maxProduct_better(String[] words) {
        // hash, length
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            final int hash = turnToInt(words[i]);
            if (!map.containsKey(hash) || map.get(hash) <= words[i].length()) {
                map.put(hash, words[i].length());
            }
        }

        final Integer[] masks = map.keySet().toArray(new Integer[]{});
        int answer = 0;
        for (int i = 0; i < masks.length; i++) {
            for (int j = i; j < masks.length; j++) {
                if ((masks[i] & masks[j]) == 0) {
                    answer = Math.max(answer, map.get(masks[i]) * map.get(masks[j]));
                }
            }
        }
        return answer;
    }

    private int turnToInt(String word) {
        int answer = 0;
        for (char c : word.toCharArray()) {
            answer |= 1 << (c-'a');
        }
        return answer;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.maxProduct(new String[]{"abcw", "baz", "foo", "bar", "xtfn", "abcdef"}));
        System.out.println(solution.maxProduct(new String[]{"a","ab","abc","d","cd","bcd","abcd"}));
        System.out.println(solution.maxProduct(new String[]{"a","aa","aaa","aaaa"}));

    }
}
