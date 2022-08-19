package org.example.basic.oj.leetcode.Q1450;

import java.util.stream.IntStream;

/**
 * @author anyuan
 * @date 2022-08-19 21:05
 */
class Solution {
    public int busyStudent(int[] startTime, int[] endTime, int queryTime) {
        return (int) IntStream.range(0, startTime.length)
                .filter(i -> startTime[i] <= queryTime && queryTime <= endTime[i])
                .count();
    }
}
