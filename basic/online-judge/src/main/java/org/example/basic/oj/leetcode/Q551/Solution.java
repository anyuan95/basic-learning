package org.example.basic.oj.leetcode.Q551;

/**
 * @author anyuan
 * @since 2021-08-17 09:07
 */
class Solution {
    public boolean checkRecord(String s) {
        int absentCounter = 0, continuousLateCounter = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'L') {
                continuousLateCounter++;
            } else {
                continuousLateCounter = 0;
                if (s.charAt(i) == 'A') {
                    absentCounter++;
                }
            }
            if (absentCounter >= 2 || continuousLateCounter >= 3) {
                return false;
            }
        }
        return true;
    }
}
