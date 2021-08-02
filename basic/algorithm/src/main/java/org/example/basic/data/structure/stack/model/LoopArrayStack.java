package org.example.basic.data.structure.stack.model;

import lombok.ToString;

/**
 * FIXME
 * @author anyuan
 * @since 2021-07-30 17:57
 */
@ToString
public class LoopArrayStack<T> implements Stack<T> {

    private Object[] array;
    private int length;
    private int index;
    private final int size;

    public LoopArrayStack(int size) {
        this.size = size;
        this.array = new Object[size];
        this.length = 0;
    }

    @Override
    public T pop() {
        if (length == 0) {
            throw new IllegalStateException("stack is empty");
        }
        length--;
        index = index == 0 ? size - 1 : index - 1;
        final T value = (T) array[index];
        array[index] = null;
        return value;
    }

    @Override
    public T push(T value) {
        if (length == size) {
            throw new IllegalStateException("stack is full");
        }
        length++;
        array[index] = value;
        index = index == size - 1 ? 0 : index + 1;
        return value;
    }

    @Override
    public T peek() {
        return null;
    }
}
