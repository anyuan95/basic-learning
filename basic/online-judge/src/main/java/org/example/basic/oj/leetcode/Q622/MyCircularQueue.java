package org.example.basic.oj.leetcode.Q622;

class MyCircularQueue {

    private int[] array;
    private int size;
    private int headIndex;
    private int tailIndex;
    private int count;

    public MyCircularQueue(int k) {
        array = new int[k];
        size = k;
        headIndex = 0;
        tailIndex = 0;
        count = 0;
    }
    
    public boolean enQueue(int value) {
        if (isFull()) {
            return false;
        }
        array[tailIndex] = value;
        tailIndex = (tailIndex + 1) % size;
        count++;
        return true;
    }
    
    public boolean deQueue() {
        if (isEmpty()) {
            return false;
        }
        array[headIndex] = -1;
        headIndex = (headIndex + 1) % size;
        count--;
        return true;
    }
    
    public int Front() {
        if (isEmpty()) {
            return -1;
        }
        return array[headIndex];
    }
    
    public int Rear() {
        if (isEmpty()) {
            return -1;
        }
        return array[(tailIndex + size - 1) % size];
    }
    
    public boolean isEmpty() {
        return count == 0;
    }
    
    public boolean isFull() {
        return count == size;
    }

    public static void main(String[] args) {
        final MyCircularQueue myCircularQueue = new MyCircularQueue(3);
        myCircularQueue.enQueue(2);
        System.out.println(myCircularQueue.Rear());
        System.out.println(myCircularQueue.Front());

    }
}