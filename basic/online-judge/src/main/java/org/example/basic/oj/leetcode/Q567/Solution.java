package org.example.basic.oj.leetcode.Q567;

import java.util.HashMap;

/**
 * 子串判断（乱序）
 *
 * @author anyuan
 * @since 2021-08-05 16:41
 */
class Solution {

    public boolean checkInclusion(String s1, String s2) {
        final int patternLength = s1.length();
        final int inputLength = s2.length();
        if (patternLength > inputLength) return false;
        HashMap<Character, Integer> patternMap = init26CharMap();
        // t2中总计只保存与t1相同数量的字符
        HashMap<Character, Integer> inputMap = init26CharMap();

        for (char c : s1.toCharArray()) {
            patternMap.compute(c, (character, integer) -> integer + 1);
        }
        for (int i = 0; i < patternLength; i++) {
            char c = s2.charAt(i);
            inputMap.compute(c, (character, integer) -> integer + 1);
        }
        if (patternMap.equals(inputMap)) {
            return true;
        }
        // 两个包含相同字符串的序列，一定包含相同数量的每种字符
        int leftIndex = 0, rightIndex = leftIndex + patternLength;
        // 实际上是使用两个指针划定一个范围，范围总长度与s1长度一致，判断范围中的table是否一致，不一致则一直向右移，直到右指针到数组边界
        while (rightIndex < inputLength) {
            // 一定present
            inputMap.computeIfPresent(s2.charAt(leftIndex), (character, integer) -> integer - 1);
            inputMap.compute(s2.charAt(rightIndex), (character, integer) -> integer + 1);
            if (patternMap.equals(inputMap)) {
                return true;
            }
            leftIndex++;
            rightIndex = leftIndex + patternLength;
        }
        return false;
    }

    private HashMap<Character, Integer> init26CharMap() {
        final HashMap<Character, Integer> map = new HashMap<>(26);
        for (int i = 'a'; i <= 'z'; i++) {
            map.put((char) i, 0);
        }
        return map;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        String s1 = "ab";
        String s2 = "eidbaooo";
        System.out.println(solution.checkInclusion(s1, s2));
    }
}
