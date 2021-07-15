package org.example.basic.data.structure.queue;

import java.util.Arrays;
import java.util.Collection;

/**
 * @author anyuan
 * @date 2020-12-25 10:36:22
 */
public class ArrayQueue<T> {

    private Object[] elementData;
    private int head = 0, tail = 0;

    public ArrayQueue() {
        elementData = new Object[10];
    }
    public ArrayQueue(int capacity) {
        elementData = new Object[capacity];
    }

    public ArrayQueue(Collection<T> collection) {
        int oldCapacity = collection.size();
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        elementData = new Object[newCapacity];
        for (T t : collection) {
            elementData[tail] = t;
            tail++;
        }
    }

    public void print() {
        System.out.println("size: " + elementData.length + ", head: " + head + ", tail: " + tail);
        System.out.println("elements: " + Arrays.toString(elementData));
    }

    public Object dequeue() {
        if (head == tail) {
            return null;
        }
        Object target = elementData[head];
        elementData[head] = null;
        head++;
        return target;
    }

    public void enqueue(T value) {
        if (tail >= elementData.length) {
            int oldCapacity = elementData.length;
            int newCapacity = oldCapacity + (oldCapacity >> 1);
            grow(newCapacity);
        }
        elementData[tail++] = value;
    }

    private void grow(int newCapacity) {
        Object[] newElementData = new Object[newCapacity];
        for (int i = 0; i < elementData.length; i++) {
            newElementData[i] = elementData[i];
        }
        elementData = newElementData;
    }

}
