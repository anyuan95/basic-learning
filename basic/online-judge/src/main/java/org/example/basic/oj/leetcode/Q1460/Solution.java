package org.example.basic.oj.leetcode.Q1460;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author anyuan
 * @date 2022-08-24 22:24
 */
class Solution {
    public boolean canBeEqual(int[] target, int[] arr) {
        Arrays.sort(target);
        Arrays.sort(arr);
        return Arrays.equals(arr, target);
    }
}
