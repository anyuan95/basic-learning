package org.example.basic.oj.leetcode.Q575;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @author anyuan
 * @date 2021-11-01 10:22
 */
class Solution {
    public int distributeCandies(int[] candyType) {
        Arrays.sort(candyType);
        final int n = candyType.length;
        int candyDiffTypeCount = 0, lastCandyType = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            if (candyType[i] != lastCandyType) {
                candyDiffTypeCount++;
                lastCandyType = candyType[i];
            }
        }
        if (candyDiffTypeCount >= n / 2) {
            // 如果糖果的种类达到或超过数组的一半，则直接给一半
            return n/2;
        } else {
            // 如果糖果的种类不到数组的一半，那就有多少种给多少种，剩下的格子都（只能）给重复的
            return candyDiffTypeCount;
        }
    }

    public int distributeCandies_faster(int[] candyType) {
        final int n = candyType.length;
        HashSet<Integer> candyTypes = new HashSet<>();
        for (int i : candyType) {
            candyTypes.add(i);
        }
        int candyDiffTypeCount = candyTypes.size();
        if (candyDiffTypeCount >= n / 2) {
            // 如果糖果的种类达到或超过数组的一半，则直接给一半
            return n/2;
        } else {
            // 如果糖果的种类不到数组的一半，那就有多少种给多少种，剩下的格子都（只能）给重复的
            return candyDiffTypeCount;
        }
    }
}
