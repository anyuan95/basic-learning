package org.example.basic.oj.leetcode.Q2100;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> goodDaysToRobBank(int[] security, int time) {
        final int n = security.length;
        if (n <= time * 2) {
            return new ArrayList<>();
        }
        int[] left = new int[n], right = new int[n];
        for (int i = 1; i < n; i++) {
            if (security[i - 1] >= security[i]) {
                left[i] = left[i - 1] + 1;
            }
        }
        for (int i = n - 2; i >= 0; i--) {
            if (security[i] <= security[i + 1]) {
                right[i] = right[i + 1] + 1;
            }
        }
        final List<Integer> answer = new ArrayList<>();
        for (int i = time; i < n; i++) {
            if (left[i] >= time && right[i] >= time) {
                answer.add(i);
            }
        }
        return answer;
    }
}
