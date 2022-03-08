package org.example.basic.oj.leetcode.Q504;

/**
 * @author anyuan
 * @date 2022-03-07 13:05
 */
class Solution {
    public String convertToBase7(int num) {
        if (num == 0) {
            return "0";
        }
        final StringBuilder builder = new StringBuilder();
        int abs = Math.abs(num);
        while (abs != 0) {
            builder.insert(0, abs % 7);
            abs /= 7;
        }
        return num < 0 ? "-" + builder.toString() : builder.toString();
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.convertToBase7(100));
        System.out.println(solution.convertToBase7(-7));
    }
}
