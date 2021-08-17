package org.example.basic.oj.leetcode.Q14;

/**
 * @author anyuan
 * @since 2021-08-17 12:12
 */
class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }
        final StringBuilder sb = new StringBuilder(strs[0]);
        // 逐个与sb进行比对
        for (int i = 1; i < strs.length; i++) {
            final String temp = strs[i];
            final int maxCompareLength = Math.min(sb.length(), temp.length());
            for (int j = 0; j < maxCompareLength; j++) {
                if (sb.charAt(j) != temp.charAt(j)) {
                    sb.delete(j, sb.length());
                    break;
                }
            }
            if (temp.length() < sb.length()) {
                sb.delete(maxCompareLength, sb.length());
            }
            if (sb.length() == 0) {
                return "";
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.longestCommonPrefix(new String[]{"ab", "a"}));
        System.out.println(solution.longestCommonPrefix(new String[]{"flower", "flow", "flight"}));
        System.out.println(solution.longestCommonPrefix(new String[]{"dog", "racecar", "car"}));
    }
}
