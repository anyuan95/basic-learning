package org.example.basic.oj.leetcode.Q1189;

class Solution {
    public int maxNumberOfBalloons(String text) {
        // balloon
        int a = 0, b = 0, l = 0, n = 0, o = 0;
        for (char c : text.toCharArray()) {
            if (c == 'a') {
                a++;
            } else if (c == 'b') {
                b++;
            } else if (c == 'l') {
                l++;
            } else if (c == 'n') {
                n++;
            } else if (c == 'o') {
                o++;
            }
        }
        int answer = Math.min(a,b);
        answer = Math.min(answer, n);
        answer = Math.min(answer, l / 2);
        answer = Math.min(answer, o / 2);
        return answer;
    }
}
