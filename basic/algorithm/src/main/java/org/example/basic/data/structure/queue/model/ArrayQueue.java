package org.example.basic.data.structure.queue.model;

/**
 * 循环数组
 *
 * @author anyuan
 * @since 2021-07-30 18:01
 */
public class ArrayQueue<T> implements Queue<T> {

    private T[] elements;
    private int limit;
    private int beginIndex;
    private int endIndex;
    private int size;

    public ArrayQueue(int limit) {
        this.limit = limit;
    }

    @Override
    public boolean add(T t) {
        endIndex = (endIndex + 1) % limit;
        elements[endIndex] = t;
        size++;
        return true;
    }

    @Override
    public boolean offer(T t) {
        beginIndex = (beginIndex + 1) % limit;
        elements[beginIndex] = null;
        size--;
        return true;
    }

    @Override
    public T remove() {
        return null;
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
