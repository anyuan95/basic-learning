package org.example.basic.oj.leetcode.Q1629;

class Solution {
    public char slowestKey(int[] releaseTimes, String keysPressed) {
        char answer = keysPressed.charAt(0);
        int slowest = releaseTimes[0];
        final int n = releaseTimes.length;
        for (int i = 1; i < n; i++) {
            int time = releaseTimes[i] - releaseTimes[i-1];
            if (time > slowest) {
                slowest = time;
                answer = keysPressed.charAt(i);
            } else if (time == slowest) {
                if (answer < keysPressed.charAt(i)) {
                    answer = keysPressed.charAt(i);
                }
            }
        }
        return answer;
    }
}
