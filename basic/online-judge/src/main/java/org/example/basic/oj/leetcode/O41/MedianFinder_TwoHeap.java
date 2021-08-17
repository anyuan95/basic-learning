package org.example.basic.oj.leetcode.O41;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * [动态]获取数据流中的中位数
 *
 * @author anyuan
 * @since 2021-08-04 16:10
 */
public class MedianFinder_TwoHeap {

    /**
     * Your MedianFinder object will be instantiated and called as such:
     * MedianFinder obj = new MedianFinder();
     * obj.addNum(num);
     * double param_2 = obj.findMedian();
     */

    /* 最多会对 addNum、findMedian 进行 50000 次调用。 */

    /**
     * 思路1：使用大小堆，通过在两个堆中移动元素，尽量地保证两个堆的大小一致。
     * 大数保存到小顶堆中，小数保存到大顶堆中，然后调整堆顶元素，保证平衡。
     */
    private PriorityQueue<Integer> minHeap;
    private PriorityQueue<Integer> maxHeap;
    private int size;

    public MedianFinder_TwoHeap() {
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        size = 0;
    }

    /**
     * 通过两个堆互相移动，保证以下两种情况：
     * 1.总元素数量为偶数时，大顶堆中元素数量 = 小顶堆中元素数量，这样中位数 = （大顶堆顶 + 小顶堆顶） / 2
     * 2.总元素数量为奇数时，大顶堆中元素数量 = 小顶堆中元素数量+1，这样中位数 = 大顶堆顶
     * <p>
     * 调整过程中，一直要保证：大顶堆顶<=小顶堆顶
     *
     * @param num
     */
    public void addNum(int num) {
        if (maxHeap.size() == 0) {
            maxHeap.add(num);
            size++;
            return;
        }
        // 仅当[大顶堆元素比小顶堆多1，且num大于等于大顶堆顶元素]时，直接将num加入小顶堆中
        if (maxHeap.size() - minHeap.size() == 1 && num >= maxHeap.peek()) {
            minHeap.add(num);
            size++;
            return;
        }
        if (minHeap.isEmpty() || num <= minHeap.peek()) {
            // 只要num比小顶堆顶小或等，就加入大顶堆
            maxHeap.add(num);
        } else {
            // 否则就是num比小顶堆顶大，肯定加入小顶堆
            minHeap.add(num);
        }

        if (maxHeap.size() - minHeap.size() > 1) {
            // 如果大顶堆已经比小顶堆多超过一个元素了，则将大顶堆顶取出放到小顶堆中，保证大顶堆.size - 小顶堆.size = 0或1
            minHeap.add(maxHeap.poll());
        } else if (maxHeap.size() < minHeap.size()) {
            // 也有可能是：本来两个堆大小相同，但是新加入的值放到了小顶堆中，这时就要把小顶堆顶弹出来放到大顶堆中
            maxHeap.add(minHeap.poll());
        }
        size++;
    }

    public double findMedian() {
        if (size == 0) {
            return -1;
        }
        if (maxHeap.size() == minHeap.size()) {
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        } else {
            return maxHeap.peek();
        }
    }

    public static void main(String[] args) {
        final MedianFinder_TwoHeap medianFinder = new MedianFinder_TwoHeap();
        medianFinder.addNum(-1);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(-2);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(-3);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(-4);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(-5);
        System.out.println(medianFinder.findMedian());
    }
}
