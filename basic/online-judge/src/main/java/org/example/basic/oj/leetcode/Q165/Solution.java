package org.example.basic.oj.leetcode.Q165;

/**
 * @author anyuan
 * @since 2021-09-01 23:38
 */
class Solution {
    public int compareVersion(String version1, String version2) {
        final String[] pieces1 = version1.split("\\.");
        final String[] pieces2 = version2.split("\\.");
        int maxLength = Math.max(pieces1.length, pieces2.length);
        for (int i = 0; i < maxLength; i++) {
            int curVersion1 = pieces1.length <= i ? 0 : Integer.parseInt(pieces1[i]);
            int curVersion2 = pieces2.length <= i ? 0 : Integer.parseInt(pieces2[i]);
            if (curVersion1 < curVersion2) {
                return -1;
            } else if (curVersion1 > curVersion2) {
                return 1;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.compareVersion("1.0.1", "1.1"));
    }
}
