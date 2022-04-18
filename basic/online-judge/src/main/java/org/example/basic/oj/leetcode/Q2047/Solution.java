package org.example.basic.oj.leetcode.Q2047;

class Solution {
    public int countValidWords(String sentence) {
        final String pattern = "([a-z]*|([a-z]+-[a-z]+))[!,.]?";
        final String[] split = sentence.split(" ");
        int answer = 0;
        for (String s : split) {
            if (s.length() > 0 && s.matches(pattern)) {
                answer++;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.countValidWords("a  b  c d   "));
    }
}
