package org.example.basic.oj.leetcode.Q3;

import java.util.HashMap;

/**
 * @author anyuan
 * @since 2021-08-08 21:56
 */
class Solution_Basic {

    public int lengthOfLongestSubstring(String s) {
        if (s.length() < 2) {
            return s.length();
        }
        // 记录每个字符上次出现的位置
        HashMap<Character, Integer> charLastOccurrenceIndex = new HashMap<>();
        final char[] chars = s.toCharArray();
        int slowPointer = 0, quickPointer = 1;
        charLastOccurrenceIndex.put(chars[slowPointer], slowPointer);
        int currentUnrepeatableLength = 1, maxUnrepeatableLength = 1;
        while (slowPointer <= quickPointer && quickPointer < chars.length) {
            if (!charLastOccurrenceIndex.containsKey(chars[quickPointer])) {
                // else就是没出现过
                // 没出现过，就继续累加长度，只有快指针右移
                charLastOccurrenceIndex.put(chars[quickPointer], quickPointer);
                quickPointer++;
                currentUnrepeatableLength++;
            } else if (charLastOccurrenceIndex.get(chars[quickPointer]) != quickPointer){
                // 这里判断记录中上次该字符出现位置是否是当前快指针的位置
                // 如果是，则说明快慢指针重合了，此时只要让快指针继续右移；
                // 如果不是，则正常处理；

                // 如果已经出现过，则说明已经重复了
                // 则：quick右移1，left移动到上次出现的位置的下一个

                // 如果直接移动到上一个出现位置的后一格，则可能出现[虽然当前元素没有重复，但是其他元素又重复了]的情况。所以添加max。
                slowPointer = Math.max(slowPointer, charLastOccurrenceIndex.get(chars[quickPointer]) + 1);
                charLastOccurrenceIndex.put(chars[quickPointer], quickPointer);
                currentUnrepeatableLength = quickPointer - slowPointer + 1;
            } else {
                quickPointer++;
            }
            maxUnrepeatableLength = Math.max(maxUnrepeatableLength, currentUnrepeatableLength);
        }
        return maxUnrepeatableLength;
    }

    public static void main(String[] args) {
        final Solution_Basic solution = new Solution_Basic();
        System.out.println(solution.lengthOfLongestSubstring("abba"));
        System.out.println(solution.lengthOfLongestSubstring("aab"));
        System.out.println(solution.lengthOfLongestSubstring("abcabcbb"));
        System.out.println(solution.lengthOfLongestSubstring("bbbbb"));
        System.out.println(solution.lengthOfLongestSubstring("pwwkew"));
        System.out.println(solution.lengthOfLongestSubstring(""));
    }
}
