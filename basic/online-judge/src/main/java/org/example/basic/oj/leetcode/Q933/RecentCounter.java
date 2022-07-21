package org.example.basic.oj.leetcode.Q933;

import java.util.LinkedList;
import java.util.Queue;

class RecentCounter {

    Queue<Integer> queue = new LinkedList<>();
    public RecentCounter() {
    }
    
    public int ping(int t) {
        queue.add(t);
        while (!queue.isEmpty() && queue.peek() < t - 3000) {
            queue.poll();
        }
        return queue.size();
    }
}