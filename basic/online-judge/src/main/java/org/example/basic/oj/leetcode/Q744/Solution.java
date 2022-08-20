package org.example.basic.oj.leetcode.Q744;

class Solution {
    // 如果没有找到比target大的，就返回letters[0]
    public char nextGreatestLetter(char[] letters, char target) {
        int left = 0, right = letters.length - 1;
        while (left < right) {
            int middle = (left + right) / 2;
            if (letters[middle] > target) {
                right = middle;
            } else {
                left = middle + 1;
            }
        }
        return letters[right] > target ? letters[right] : letters[0];
    }

    public static void main(String[] args) {

        final Solution solution = new Solution();
        System.out.println(solution.nextGreatestLetter(new char[]{'c', 'f', 'j'}, 'g'));
    }
}
