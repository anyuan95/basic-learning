package org.example.basic.oj.leetcode.Q3;

import java.util.HashMap;

/**
 * @author anyuan
 * @since 2021-08-08 22:34
 */
public class Solution_SlidingWindow {

    public int lengthOfLongestSubstring(String s) {
        if (s.length() < 2) {
            return s.length();
        }
        // 记录每个字符上次出现的位置
        HashMap<Character, Integer> charLastOccurrenceIndex = new HashMap<>();
        int maxLength = 0, leftIndex = 0;
        for (int rightIndex = 0; rightIndex < s.length(); rightIndex++) {
            if (charLastOccurrenceIndex.containsKey(s.charAt(rightIndex))) {
                leftIndex = Math.max(leftIndex, charLastOccurrenceIndex.get(s.charAt(rightIndex)) + 1);
            }
            charLastOccurrenceIndex.put(s.charAt(rightIndex), rightIndex);
            maxLength = Math.max(maxLength, rightIndex - leftIndex + 1);
        }
        return maxLength;
    }

}
