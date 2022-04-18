package org.example.basic.oj.leetcode.Q1332;

class Solution {
    public int removePalindromeSub(String s) {
//        final boolean a = s.contains("a");
//        final boolean b = s.contains("b");
//        if (a && b) {
            // 判断是否整体是回文
            final int n = s.length();
            for (int i = 0; i < n / 2 + 1; i++) {
                if (s.charAt(i) != s.charAt(n-1-i)) {
                    return 2;
                }
            }
            return 1;
//        } else if (a || b) {
//            return 1;
//        }
//        return 1;
    }
}
