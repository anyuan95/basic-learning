package org.example.basic.oj.leetcode.Q539;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author anyuan
 * @date 2022-01-18 15:49
 */
class Solution {
    /**
     * pq
     *
     * @param timePoints
     * @return
     */
    public int _findMinDifference(List<String> timePoints) {
        final PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o2[1] - o1[1];
            }
            return o2[0] - o1[0];
        });
        for (String timePoint : timePoints) {
            final String[] split = timePoint.split(":");
            final int hour = Integer.parseInt(split[0]);
            final int minute = Integer.parseInt(split[1]);
            pq.add(new int[]{hour, minute});
            if (hour < 12) {
                pq.add(new int[]{hour + 24, minute});
            }
        }
        int minMinuteDiff = Integer.MAX_VALUE;
        int[] lastTime = pq.poll();
        while (!pq.isEmpty()) {
            final int[] thisTime = pq.poll();
            int timeDiff = (lastTime[0] - thisTime[0]) * 60 + lastTime[1] - thisTime[1];
            if (timeDiff == 0) {
                return 0;
            }
            minMinuteDiff = Math.min(timeDiff, minMinuteDiff);
            lastTime = thisTime;
        }
        return minMinuteDiff;
    }

    /**
     * list, sort
     *
     * @param timePoints
     * @return
     */
    public int __findMinDifference(List<String> timePoints) {
        final List<Integer> minuteList = new ArrayList<>();
        for (String timePoint : timePoints) {
            final String[] split = timePoint.split(":");
            final int hour = Integer.parseInt(split[0]);
            final int minute = Integer.parseInt(split[1]);
            final int minutes = hour * 60 + minute;
            minuteList.add(minutes);
            if (minutes < 1440) {
                minuteList.add(minutes + 1440);
            }
        }
        minuteList.sort(Comparator.comparingInt(o -> o));
        int minDiff = Integer.MAX_VALUE;
        final int n = minuteList.size();
        for (int i = 1; i < n; i++) {
            minDiff = Math.min(minDiff, minuteList.get(i) - minuteList.get(i - 1));
        }
        return minDiff;
    }

    /**
     * bucket
     *
     * @param timePoints
     * @return
     */
    public int findMinDifference(List<String> timePoints) {
        if (timePoints.size() > 1440) {
            return 0;
        }
        final int length = 1440 * 2;
        int[] buckets = new int[length];
        for (String timePoint : timePoints) {
            final String[] split = timePoint.split(":");
            final int hour = Integer.parseInt(split[0]);
            final int minute = Integer.parseInt(split[1]);
            final int minutes = hour * 60 + minute;
            buckets[minutes]++;
            buckets[minutes + 1440]++;
        }
        int lastMinutes = -1;
        int minDiff = Integer.MAX_VALUE;
        for (int i = 0; i < buckets.length; i++) {
            if (buckets[i] == 0) {
                continue;
            } else if (buckets[i] > 1) {
                return 0;
            } else if (lastMinutes != -1) {
                minDiff = Math.min(minDiff, i - lastMinutes);
            }
            lastMinutes = i;
        }
        return minDiff;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.findMinDifference(Lists.newArrayList("23:59", "00:00")));
        System.out.println(solution.findMinDifference(Lists.newArrayList("00:00","23:59","00:00")));
        System.out.println(solution.findMinDifference(Lists.newArrayList("12:12", "00:13")));
    }
}
