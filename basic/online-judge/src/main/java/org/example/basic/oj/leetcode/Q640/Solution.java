package org.example.basic.oj.leetcode.Q640;

import java.util.Arrays;

/**
 * @author anyuan
 * @date 2022-08-10 22:56
 */
class Solution {
    public String solveEquation(String equation) {
        String left = equation.split("=")[0];
        String right = equation.split("=")[1];
        // 求出左侧x系数和实数



        String[] pieces = equation.split("[+\\-=]");
        System.out.println(Arrays.toString(pieces));
        return null;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        s.solveEquation("x+5-3+x=6+x-2");
    }
}
