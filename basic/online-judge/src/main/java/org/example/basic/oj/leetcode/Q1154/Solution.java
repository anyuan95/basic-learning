package org.example.basic.oj.leetcode.Q1154;

/**
 * @author anyuan
 * @date 2021-12-21 19:21
 */
class Solution {
    static int[] monthDays = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public int dayOfYear(String date) {
        final char[] chars = date.toCharArray();
        final int year = (chars[0] - '0') * 1000 + (chars[1] - '0') * 100 + (chars[2] - '0') * 10 + (chars[3] - '0');
        final int month = (chars[5] - '0') * 10 + (chars[6] - '0');
        final int day = (chars[8] - '0') * 10 + (chars[9] - '0');
        boolean isLeapYear = year % 100 == 0 ? year % 400 == 0 : year % 4 == 0;
        if (isLeapYear) {
            monthDays[1] = 29;
        } else {
            monthDays[1] = 28;
        }
        int count = 0;
        for (int i = 0; i < month - 1; i++) {
            count += monthDays[i];
        }
        count += day;
        return count;
    }
}
