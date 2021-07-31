package org.example.basic.data.structure.stack.model;

/**
 * TODO
 * @author anyuan
 * @since 2021-07-30 17:57
 */
public class ArrayStack<T> implements Stack<T> {

    private T[] array;
    private int limit;

    public ArrayStack(int limit) {
        this.limit = limit;
    }

    @Override
    public T pop() {
        return null;
    }

    @Override
    public T push() {
        return null;
    }

    @Override
    public T peek() {
        return null;
    }
}
