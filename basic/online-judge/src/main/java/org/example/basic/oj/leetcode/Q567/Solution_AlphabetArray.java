package org.example.basic.oj.leetcode.Q567;

import java.util.Arrays;

/**
 * 判断s2是否包含s1的某种排列
 *
 * @author anyuan
 * @since 2021-08-18 18:38
 */
public class Solution_AlphabetArray {
    /**
     * 双指针 + 字符数组
     * <p>
     * 优化了一点点，把map换成int数组了
     *
     * @param s1
     * @param s2
     * @return
     */
    public boolean checkInclusion(String s1, String s2) {
        final int[] pattern = new int[26];
        for (char c : s1.toCharArray()) {
            pattern[c - 'a']++;
        }
        final int length1 = s1.length();
        final int length2 = s2.length();
        if (length1 > length2) {
            return false;
        }

        final char[] chars2 = s2.toCharArray();
        final int[] input = new int[26];
        for (int i = 0; i < length1; i++) {
            input[chars2[i] - 'a']++;
        }
        if (Arrays.equals(pattern, input)) {
            return true;
        }

        for (int i = 1; i < length2 - length1 + 1; i++) {
            // 去掉左
            input[chars2[i - 1] - 'a']--;
            // 加进右
            input[chars2[i + length1 - 1] - 'a']++;
            if (Arrays.equals(pattern, input)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        final Solution_AlphabetArray solution = new Solution_AlphabetArray();
        System.out.println(solution.checkInclusion("ab", "eidbaooo"));
        System.out.println(solution.checkInclusion("ab", "eidboaoo"));
    }


}
