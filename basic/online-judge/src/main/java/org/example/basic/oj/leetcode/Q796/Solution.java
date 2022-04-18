package org.example.basic.oj.leetcode.Q796;

/**
 * @author anyuan
 * @date 2022-04-07 23:25
 */
class Solution {
    public boolean _rotateString(String s, String goal) {
        if (s.length() != goal.length()) {
            return false;
        }
        final int n = s.length();
        for (int i = 0; i < n; i++) {
            if (goal.equals(s.substring(i, n) + s.substring(0, i))) {
                return true;
            }
        }
        return false;
    }

    public boolean rotateString(String s, String goal) {
        return s.length() == goal.length() && (s + s).contains(goal);
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.rotateString("abcde", "deabc"));
    }
}
