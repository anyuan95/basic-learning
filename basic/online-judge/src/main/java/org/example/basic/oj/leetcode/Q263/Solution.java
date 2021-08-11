package org.example.basic.oj.leetcode.Q263;

/**
 * @author anyuan
 * @since 2021-08-09 23:34
 */
class Solution {
    public boolean isUgly(int n) {
        if (n < 1) {
            return false;
        }
        while (n % 2 == 0) {
            n /= 2;
        }
        while (n % 3 == 0) {
            n /= 3;
        }
        while (n % 5 == 0) {
            n /= 5;
        }
        return n == 1;
    }
}
