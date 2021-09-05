package org.example.basic.oj.leetcode.I17_14;

import java.util.PriorityQueue;

/**
 * @author anyuan
 * @since 2021-09-03 08:56
 */
class Solution_heap {
    public int[] smallestK(int[] arr, int k) {
        if (k == 0) {
            return new int[0];
        }
        if (arr.length < k) {
            return arr;
        }
        final PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int i : arr) {
            minHeap.offer(i);
        }
        int[] answer = new int[k];
        for (int i = 0; i < k; i++) {
            answer[i] = minHeap.poll();
        }
        return answer;
    }
}
