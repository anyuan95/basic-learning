package org.example.basic.oj.leetcode.O41;

/**
 * 获取数据流中的中位数
 *
 * @author anyuan
 * @since 2021-08-04 16:10
 */
public class MedianFinder {

    /**
     * Your MedianFinder object will be instantiated and called as such:
     * MedianFinder obj = new MedianFinder();
     * obj.addNum(num);
     * double param_2 = obj.findMedian();
     */

    /* 最多会对 addNum、findMedian 进行 50000 次调用。 */

    public static final int MAX_SIZE = 50000;
    private int[] elements;
    private int elementCount;


    public MedianFinder() {
        this.elements = new int[MAX_SIZE];
        this.elementCount = 0;
    }

    public void addNum(int num) {

    }

    public double findMedian() {
        return 0;
    }
}
