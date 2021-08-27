package org.example.basic.oj.leetcode.Q295;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 做一个随时能够获取中位数的数据结构
 *
 * @author anyuan
 * @since 2021-08-27 09:15
 */
class MedianFinder {

    // 保存小的部分，需要是大顶堆
    private PriorityQueue<Integer> smallerHeap = new PriorityQueue<>(Comparator.reverseOrder());
    // 保存大的部分，需要是小顶堆
    private PriorityQueue<Integer> biggerHeap = new PriorityQueue<>();
    private Integer size = 0;

    /**
     * initialize your data structure here.
     */
    public MedianFinder() {
    }

    public void addNum(int num) {
        // 永远保持sh比bh多0或1个值
        if (biggerHeap.isEmpty() || num <= biggerHeap.peek()) {
            smallerHeap.add(num);
        } else {
            biggerHeap.add(num);
        }
        if (smallerHeap.size() - biggerHeap.size() > 1) {
            biggerHeap.offer(smallerHeap.poll());
        } else if (smallerHeap.size() < biggerHeap.size()) {
            smallerHeap.offer(biggerHeap.poll());
        }
        size++;
    }

    public double findMedian() {
        return size <= 0 ? 0d
                : size % 2 == 0 ? (smallerHeap.peek() + biggerHeap.peek()) / 2.0
                : smallerHeap.peek();
    }

    public static void main(String[] args) {
        final MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(1);
        medianFinder.addNum(2);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(3);
        System.out.println(medianFinder.findMedian());
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */