package org.example.basic.oj.leetcode.Q1374;

import java.util.stream.IntStream;

class Solution {
    public String generateTheString(int n) {
        if (n == 1) {
            return "a";
        } else if (n % 2 == 0) {
            final StringBuilder builder = new StringBuilder();
            for (int i = 0; i < n - 1; i++) {
                builder.append('a');
            }
            builder.append('b');
            return builder.toString();
        } else {
            final StringBuilder builder = new StringBuilder();
            for (int i = 0; i < n - 2; i++) {
                builder.append('a');
            }
            builder.append("bc");
            return builder.toString();
        }
    }
}
