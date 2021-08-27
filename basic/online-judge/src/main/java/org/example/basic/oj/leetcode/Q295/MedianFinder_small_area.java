package org.example.basic.oj.leetcode.Q295;

/**
 * 如果给定的所有值都在0-100之间
 *
 * @author anyuan
 * @since 2021-08-27 09:27
 */
class MedianFinder_small_area {

    // 0~100  101个数
    private int[] buckets = new int[101];
    // 中位数
    private int median = 0;
    // 中位数（或中位数左边的数）在当前桶中的位置
    private int medianIndexInCurrentBucket = -1;
    private int size = 0;

    /**
     * initialize your data structure here.
     */
    public MedianFinder_small_area() {
    }

    public void addNum(int num) {
        buckets[num]++;
        // 如果插入的值比中位数所在桶的值大，则往右移
        if (size % 2 == 0) {
            if (num >= median) {
                moveRight();
            }
        } else {
            if (num < median) {
                moveLeft();
            }
        }
        size++;
    }

    private void moveLeft() {
        if (medianIndexInCurrentBucket == 0) {
            while (--median >= 0) {
                if (buckets[median] != 0) {
                    medianIndexInCurrentBucket = buckets[median] - 1;
                    return;
                }
            }
        } else {
            medianIndexInCurrentBucket--;
        }
    }

    private void moveRight() {
        if (medianIndexInCurrentBucket == buckets[median] - 1) {
            while (++median <= 101) {
                if (buckets[median] != 0) {
                    medianIndexInCurrentBucket = 0;
                    return;
                }
            }
        } else {
            medianIndexInCurrentBucket++;
        }
    }

    public int getNext(int bucketIndex, int indexInBucket) {
        // 如果当前值在所属桶中已经是最后一个值，则返回下一个（非空）桶的第一个值
        if (indexInBucket == buckets[bucketIndex] - 1) {
            while (++bucketIndex <= 101) {
                if (buckets[bucketIndex] != 0) {
                    return bucketIndex;
                }
            }
            // 遍历完所有桶发现后边的桶都是空的，返回-1
            return -1;
        } else {
            return bucketIndex;
        }
    }

    public int getLast(int bucketIndex, int indexInBucket) {
        if (indexInBucket == 0) {
            while (--bucketIndex >= 0) {
                if (buckets[bucketIndex] != 0) {
                    return bucketIndex;
                }
            }
            return -1;
        } else {
            return bucketIndex;
        }
    }

    public double findMedian() {
        return size <= 0 ? 0d
                : size % 2 == 0 ? (median + getNext(median, medianIndexInCurrentBucket)) / 2.0
                : median;
    }

    public static void main(String[] args) {
        final MedianFinder_small_area medianFinder_small_area = new MedianFinder_small_area();
        medianFinder_small_area.addNum(1);
        System.out.println(medianFinder_small_area.findMedian());
        medianFinder_small_area.addNum(2);
        System.out.println(medianFinder_small_area.findMedian());
        medianFinder_small_area.addNum(3);
        System.out.println(medianFinder_small_area.findMedian());
        medianFinder_small_area.addNum(4);
        System.out.println(medianFinder_small_area.findMedian());
        medianFinder_small_area.addNum(4);
        System.out.println(medianFinder_small_area.findMedian());
        medianFinder_small_area.addNum(3);
        System.out.println(medianFinder_small_area.findMedian());
        medianFinder_small_area.addNum(2);
        System.out.println(medianFinder_small_area.findMedian());
        medianFinder_small_area.addNum(1);
        System.out.println(medianFinder_small_area.findMedian());
    }
}
