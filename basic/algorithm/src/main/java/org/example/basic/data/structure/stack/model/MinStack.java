package org.example.basic.data.structure.stack.model;

import lombok.ToString;

import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * 最小栈，要求任何时候都能够获取栈中最小值，O(1)
 *
 * @author anyuan
 * @since 2021-07-31 18:39
 */
@ToString
public class MinStack<T extends Comparable> {

    private final Object[] values;
    private final Object[] minValues;
    private int index; //下一个值该放的位置
    private int length;
    private final int maxSize;

    public MinStack(int maxSize) {
        this.maxSize = maxSize;
        this.values = new Object[maxSize];
        this.minValues = new Object[maxSize];
        this.index = 0;
        this.length = 0;
    }

    private void pushMin(T value) {
        if (length == 0 || length == 1) {
            this.minValues[index] = value;
        } else {
            this.minValues[index] = value.compareTo(this.values[index - 1]) > 0 ? this.values[index - 1] : value;
        }
    }

    private T popMin() {
        final T minValue = (T) this.minValues[index - 1];
        this.minValues[index - 1] = null;
        return minValue;
    }

    public T push(T value) {
        if (length == maxSize) {
            throw new IllegalStateException("STACK IS FULL");
        }
        pushMin(value);
        this.values[index] = value;
        index++;
        length++;
        return value;
    }

    public T pop() {
        if (length == 0) {
            throw new IllegalStateException("STACK IS EMPTY");
        }
        popMin();
        T value = (T) this.values[index - 1];
        this.values[index - 1] = null;
        index--;
        length--;
        return value;
    }

    public T getMin() {
        return (T) this.minValues[index - 1];
    }
}
