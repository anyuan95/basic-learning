package org.example.basic.oj.leetcode.Q693;

class Solution {
    public boolean hasAlternatingBits(int n) {
        // 01111...111
        int temp = n ^ (n >> 1);
        return (temp & (temp + 1)) == 0;
    }
}
