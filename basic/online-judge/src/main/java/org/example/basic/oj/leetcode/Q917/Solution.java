package org.example.basic.oj.leetcode.Q917;

class Solution {
    public String reverseOnlyLetters(String s) {
        final char[] chars = s.toCharArray();
        final int n = chars.length;
        int startPos = 0, endPos = n - 1;
        while (startPos < endPos) {
            while (!isLetter(chars[startPos]) && startPos < endPos) {
                startPos++;
            }
            while (!isLetter(chars[endPos]) && startPos < endPos) {
                endPos--;
            }
            swap(chars, startPos, endPos);
            startPos++;
            endPos--;
        }
        return new String(chars);
    }

    private boolean isLetter(char c) {
        return (c >= 'a' && c <='z') || (c >= 'A' && c <='Z');
    }

    private void swap(char[] arr, int idx1, int idx2) {
        char temp = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = temp;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.reverseOnlyLetters("a-bC-dEf-ghIj"));
        System.out.println(solution.reverseOnlyLetters("ab-cd"));
        System.out.println(solution.reverseOnlyLetters("ab-ecd"));
    }
}
