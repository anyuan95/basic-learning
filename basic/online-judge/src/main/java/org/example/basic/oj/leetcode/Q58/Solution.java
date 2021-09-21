package org.example.basic.oj.leetcode.Q58;

/**
 * @author anyuan
 * @since 2021-09-21 21:36
 */
class Solution {
    public int lengthOfLastWord(String s) {
        int n = s.length() - 1;
        int answer = 0;
        while (n >= 0 && s.charAt(n) == ' ') {
            n--;
        }
        while (n >= 0 && s.charAt(n) != ' ') {
            answer++;
            n--;
        }
        return answer;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.lengthOfLastWord("Hello World"));
        System.out.println(solution.lengthOfLastWord("   fly me   to   the moon  "));
        System.out.println(solution.lengthOfLastWord("luffy is still joyboy"));
        System.out.println(solution.lengthOfLastWord("aaa"));
    }
}
