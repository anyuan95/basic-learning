package org.example.basic.oj.leetcode.Q3;

import java.util.HashMap;

/**
 * @author anyuan
 * @since 2021-08-08 22:34
 */
public class Solution_SlidingWindow {

    /**
     * 滑动窗口题目:
     *
     * 3. 无重复字符的最长子串
     *
     * 30. 串联所有单词的子串
     *
     * 76. 最小覆盖子串
     *
     * 159. 至多包含两个不同字符的最长子串
     *
     * 209. 长度最小的子数组
     *
     * 239. 滑动窗口最大值
     *
     * 567. 字符串的排列
     *
     * 632. 最小区间
     *
     * 727. 最小窗口子序列
     *
     */

    /**
     * 滑动窗口
     * @param s
     * @return
     */
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
