package org.example.basic.oj.leetcode.Q641;

class MyCircularDeque {

    private int[] array;
    private int length;
    // 当前第一个元素的位置
    private int headIndex;
    // 当前最后一个元素的位置
    private int tailIndex;
    private int elementCount = 0;

    public MyCircularDeque(int k) {
        this.array = new int[k];
        this.length = k;
    }

    public boolean insertFront(int value) {
        if (isFull()) {
            return false;
        }
        headIndex = (headIndex + length - 1) % length;
        array[headIndex] = value;
        elementCount++;
        return true;
    }

    public boolean insertLast(int value) {
        if (isFull()) {
            return false;
        }
        array[tailIndex] = value;
        tailIndex = (tailIndex + 1) % length;
        elementCount++;
        return true;
    }

    public boolean deleteFront() {
        if (isEmpty()) {
            return false;
        }
        headIndex = (headIndex + 1) % length;
        elementCount--;
        return true;
    }

    public boolean deleteLast() {
        if (isEmpty()) {
            return false;
        }
        tailIndex = (tailIndex + length - 1) % length;
        elementCount--;
        return true;
    }

    public int getFront() {
        if (isEmpty()) {
            return -1;
        }
        return array[headIndex];
    }

    public int getRear() {
        if (isEmpty()) {
            return -1;
        }
        return array[(tailIndex + length - 1) % length];
    }

    public boolean isEmpty() {
        return elementCount == 0;
    }

    public boolean isFull() {
        return elementCount == length;
    }
}