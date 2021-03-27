package org.example.sort.model;

import lombok.Getter;

import java.lang.reflect.Array;

/**
 * TODO 有序集合
 *
 * @author anyuan
 * @since 2021-03-12 17:29
 */
@Getter
public class SortedArray<T> {

    private T[] array;

    private int capacity;

    public SortedArray(Class<T> clazz, Integer length) {
        this.array = (T[]) Array.newInstance(clazz, length);
    }

    public void insert(T value) {
        final int hashCode = value.hashCode();

    }

}
