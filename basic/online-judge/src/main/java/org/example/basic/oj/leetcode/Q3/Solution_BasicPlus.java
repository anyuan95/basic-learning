package org.example.basic.oj.leetcode.Q3;

import java.util.HashMap;

/**
 * @author anyuan
 * @since 2021-08-18 16:16
 */
class Solution_BasicPlus {

    /**
     * 比basic稍微好一点，也更好理解一点
     *
     * 用 [map.contains] + [map.get >= leftPointer] 判断是否是遇到了重复字符串，以此决定左指针是否需要跳转。
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        if (s == null) {
            return -1;
        }
        if (s.length() < 2) {
            return s.length();
        }
        int maxLength = 0, currentMaxLength = 1;
        int leftPointer = 0, rightPointer = 1;
        HashMap<Character, Integer> charLastAppearIndex = new HashMap<>();
        charLastAppearIndex.put(s.charAt(leftPointer), leftPointer);
        while (leftPointer <= rightPointer && rightPointer < s.length()) {
            final char rightChar = s.charAt(rightPointer);
            // 记录字符串上次出现位置的map中有右指针当前的字符，且!!!这个记录的位置要大于等于左指针位置! 这是因为左指针总是会向右跳，跳过的字符串仍然保留在map中。
            if (charLastAppearIndex.containsKey(rightChar) && charLastAppearIndex.get(rightChar) >= leftPointer) {
                final Integer rightCharLastAppearIndex = charLastAppearIndex.get(rightChar);
                leftPointer = rightCharLastAppearIndex + 1;
                charLastAppearIndex.put(rightChar, rightPointer);

                maxLength = Math.max(maxLength, currentMaxLength);
                currentMaxLength = rightPointer - leftPointer + 1;
            } else {
                charLastAppearIndex.put(rightChar, rightPointer);
                currentMaxLength++;
                maxLength = Math.max(maxLength, currentMaxLength);
            }
            rightPointer++;
        }
        return maxLength;
    }

    public static void main(String[] args) {
        final Solution_BasicPlus solution = new Solution_BasicPlus();
        System.out.println(solution.lengthOfLongestSubstring("ab"));
        System.out.println(solution.lengthOfLongestSubstring("aa"));
        System.out.println(solution.lengthOfLongestSubstring("pwwkew"));
        System.out.println(solution.lengthOfLongestSubstring("bbbbb"));
        System.out.println(solution.lengthOfLongestSubstring("abcabcbb"));
    }
}
