package org.example.basic.data.structure.queue.model;

import lombok.ToString;

/**
 * 循环数组
 *
 * @author anyuan
 * @since 2021-07-30 18:01
 */
@ToString
public class LoopArrayQueue<T> implements Queue<T> {

    private Object[] elements;
    private int length;
    // 注意，beginIndex是队首元素的位置
    private int beginIndex;
    // 注意，endIndex是下一个要插入的元素保存的位置
    private int endIndex;
    private int size;

    public LoopArrayQueue(int size) {
        this.length = 0;
        this.beginIndex = 0;
        this.endIndex = 0;
        this.size = size;
        this.elements = new Object[size];
    }

    @Override
    public boolean add(T t) {
        elements[endIndex] = t;
        endIndex = (endIndex + 1) % size;
        length++;
        return true;
    }

    @Override
    public boolean offer(T t) {
        return false;
    }

    @Override
    public T remove() {
        final T value = (T) elements[beginIndex];
        elements[beginIndex] = null;
        beginIndex = (beginIndex + 1) % size;
        length--;
        return value;
    }

    @Override
    public T poll() {
        return null;
    }

    @Override
    public T element() {
        return null;
    }

    @Override
    public T peek() {
        return null;
    }
}
