package org.example.basic.oj.leetcode.Q630;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author anyuan
 * @date 2021-12-14 10:14
 */
class Solution {
    /**
     * 这里有 n 门不同的在线课程，按从 1 到 n 编号。给你一个数组 courses ，
     * 其中 courses[i] = [durationi, lastDayi] 表示第 i 门课将会 持续 上 durationi 天课，并且必须在不晚于 lastDayi 的时候完成。
     * <p>
     * 你的学期从第 1 天开始。且不能同时修读两门及两门以上的课程。
     * <p>
     * 返回你最多可以修读的课程数目。
     *
     * 输入：courses = [[100, 200], [200, 1300], [1000, 1250], [2000, 3200]]
     * 输出：3
     *
     * @param courses
     * @return
     */
    public int scheduleCourse(int[][] courses) {
        // 事先处理：去掉所有last < duration的
        // 贪心：按时间升序排序，然后优先拿耗时短的
        Arrays.sort(courses, Comparator.comparingInt(o -> o[1]));
        // pq用来存课程耗时，大顶堆
        final PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2-o1);
        int timeUsed = 0;
        for (int[] course : courses) {
            if (course[0] > course[1]) {
                continue;
            }
            int duration = course[0], endTime = course[1];
            timeUsed += duration;
            queue.add(duration);
            if (timeUsed > endTime) {
                timeUsed -= queue.poll();
            }
        }
        return queue.size();
    }
}
