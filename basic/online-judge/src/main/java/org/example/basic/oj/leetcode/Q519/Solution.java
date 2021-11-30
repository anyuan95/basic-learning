package org.example.basic.oj.leetcode.Q519;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * @author anyuan
 * @since 2021-11-27 22:02
 */
class Solution {

    private Set<Integer> set = new HashSet<>();
    private int m, n;

    public Solution(int m, int n) {
        this.m = m;
        this.n = n;
    }

    public int[] flip() {
        int index;
        do {
            index = new Random().nextInt(m * n);
        } while (set.contains(index));
        set.add(index);
        return new int[]{index / n, index % m};
    }

    public void reset() {
        set.clear();
    }
}
