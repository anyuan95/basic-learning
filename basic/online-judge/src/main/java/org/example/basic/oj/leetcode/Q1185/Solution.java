package org.example.basic.oj.leetcode.Q1185;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;

class Solution {
    /**
     * 1971-01-01是星期五
     *
     * @param day
     * @param month
     * @param year
     * @return
     */
    public String dayOfTheWeek(int day, int month, int year) {
        int dayCount = 0;
        final int leapYears = (year / 4 - year / 100 + year / 400) - (1971 / 4 - 1971 / 100 + 1971 / 400);
        dayCount += (year - 1971) * 365 + leapYears;
        final boolean isLeapYear = (year % 100 != 0 && year % 4 == 0) || year % 400 == 0;
        if (isLeapYear) {
            dayCount -= 1;
            monthDays[1] = 29;
        } else {
            monthDays[1] = 28;
        }
        for (int i = 0; i < month - 1; i++) {
            dayCount += monthDays[i];
        }
        dayCount += day - 1;
        System.out.println(dayCount);
        System.out.println(DateUtil.between(DateUtil.parseDate(year + "-" + month + "-" + day), DateUtil.parseDate("1971-01-01"), DateUnit.DAY));
        return daysInWeek[(dayCount % 7 + 4) % 7];
    }

    private static final int[] monthDays = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private static final String[] daysInWeek = new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

    public static void main(String[] args) {
        final Solution solution = new Solution();
//        System.out.println(solution.dayOfTheWeek(3, 1, 2022));
//        System.out.println(solution.dayOfTheWeek(1, 1, 1971));
//        System.out.println(solution.dayOfTheWeek(29, 2, 2016));
//        System.out.println(solution.dayOfTheWeek(11, 7, 1983));
        System.out.println(solution.dayOfTheWeek(28, 2, 2100));
        System.out.println(solution.dayOfTheWeek(1, 1, 2100));
    }
}
