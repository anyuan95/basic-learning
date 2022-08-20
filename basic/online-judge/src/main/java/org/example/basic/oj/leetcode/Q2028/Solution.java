package org.example.basic.oj.leetcode.Q2028;

import java.util.Arrays;

class Solution {
    public int[] missingRolls(int[] rolls, int mean, int n) {
        int nSum = mean * (rolls.length + n);
        for (int roll : rolls) {
            nSum -= roll;
        }
        if (nSum < n || nSum > n * 6) {
            return new int[0];
        }
        final int avg = nSum / n;
        // 全部用平均值（向下取整）填充，然后把剩下的值逐步加到数组各个位置
        final int[] answer = new int[n];
        Arrays.fill(answer, avg);
        nSum -= avg * n;
        int index = 0;
        // 这个循环最多只会执行n-1次
        while (nSum > 0 && index < n) {
            answer[index]++;
            nSum--;
            index++;
        }
        return answer;
    }
}
