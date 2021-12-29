package org.example.basic.oj.leetcode.Q472_unable_dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * FIXME COPIED
 * @author anyuan
 * @date 2021-12-28 23:59
 */
class Solution {
    Set<Long> set = new HashSet<>();
    int P = 131, OFFSET = 128;
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        for (String s : words) {
            long hash = 0;
            for (char c : s.toCharArray()) hash = hash * P + (c - 'a') + OFFSET;
            set.add(hash);
        }
        List<String> ans = new ArrayList<>();
        for (String s : words) {
            if (check(s)) ans.add(s);
        }
        return ans;
    }
    boolean check(String s) {
        int n = s.length();
        int[] f = new int[n + 1];
        Arrays.fill(f, -1);
        f[0] = 0;
        for (int i = 0; i <= n; i++) {
            if (f[i] == -1) continue;
            long cur = 0;
            for (int j = i + 1; j <= n; j++) {
                cur = cur * P + (s.charAt(j - 1) - 'a') + OFFSET;
                if (set.contains(cur)) f[j] = Math.max(f[j], f[i] + 1);
            }
            if (f[n] > 1) return true;
        }
        return false;
    }
}