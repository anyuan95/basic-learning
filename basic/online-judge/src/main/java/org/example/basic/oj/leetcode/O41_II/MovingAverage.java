package org.example.basic.oj.leetcode.O41_II;

import java.util.LinkedList;
import java.util.Queue;

class MovingAverage {

    int targetSize;
    Queue<Integer> queue;
    int currentSum;

    /**
     * Initialize your data structure here.
     */
    public MovingAverage(int size) {
        this.targetSize = size;
        this.queue = new LinkedList<>();
        this.currentSum = 0;
    }

    public double next(int val) {
        if (queue.size() >= targetSize) {
            currentSum -= queue.poll();
        }
        queue.add(val);
        currentSum += val;
        return currentSum * 1.0d / Math.min(targetSize, queue.size());
    }
}

/**
 * Your MovingAverage object will be instantiated and called as such:
 * MovingAverage obj = new MovingAverage(size);
 * double param_1 = obj.next(val);
 */