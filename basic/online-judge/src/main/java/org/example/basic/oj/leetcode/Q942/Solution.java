package org.example.basic.oj.leetcode.Q942;

class Solution {
    public int[] diStringMatch(String s) {
        final int n = s.length();
        int index = 0;
        int[] answer = new int[n+1];
        int smaller = 0, bigger = n;
        final char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++, index++) {
            if (chars[i] == 'I') {
                // 尽可能放小的
                answer[index] = smaller;
                smaller++;
            } else if (chars[i] == 'D') {
                answer[index] = bigger;
                bigger--;
            }
        }
        answer[n] = smaller;
        return answer;
    }
}