package org.example.basic.oj.leetcode.Q2712;

class Solution {
    public long minimumCost(String s) {
        // 优化解法
        // 对于任意下标i，如果其与前一个下标i-1不一致，则一定要进行翻转。
        // 此时有且只会有两种解决方式：翻转左侧(0~i-1,成本i)，或者翻转右侧(i~n-1,成本n-i)
        // 故最优解一定在上面两种方式之中，只要取较小的即可
        int n = s.length();
        long minCost = 0;
        char[] chars = s.toCharArray();
        for (int i = 1; i < n; i++) {
            if (chars[i] != chars[i-1]) {
                minCost += Math.min(i, n-i);
            }
        }
        return minCost;
    }
}