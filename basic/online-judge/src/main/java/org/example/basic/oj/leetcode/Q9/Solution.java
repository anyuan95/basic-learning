package org.example.basic.oj.leetcode.Q9;

import java.util.Stack;

/**
 * @author anyuan
 * @since 2021-08-17 11:29
 */
class Solution {
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        if (x < 10) {
            return true;
        }
//        int digits = (int) Math.log10(x) + 1;
        int digits = 0, tempx = x;
        while (tempx != 0) {
            digits++;
            tempx /= 10;
        }
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < digits / 2; i++) {
            stack.push(x % 10);
            x /= 10;
        }
        if (digits % 2 != 0) {
            x /= 10;
        }
        for (int i = 0; i < digits / 2; i++) {
            Integer temp = stack.pop();
            if (temp != x % 10) {
                return false;
            }
            x /= 10;
        }
        return true;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.isPalindrome(121));
        System.out.println(solution.isPalindrome(-121));
        System.out.println(solution.isPalindrome(221));
        System.out.println(solution.isPalindrome(1221));
        System.out.println(solution.isPalindrome(0));
    }
}
