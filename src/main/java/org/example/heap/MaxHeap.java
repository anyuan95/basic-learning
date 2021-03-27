package org.example.heap;

import cn.hutool.core.util.ArrayUtil;
import lombok.ToString;

import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 * @author anyuan
 * @since 2021-03-16 08:52
 */
@ToString
public class MaxHeap<T extends Comparable> {

    // 从1开始存储
    private T[] array;
    // 可以存储的最大数据个数
    private int size;
    // 已存储的数据个数
    private int elementCount;

    private MaxHeap(T[] sortedArray) {
        this.array = sortedArray;
        this.size = sortedArray.length;
        this.elementCount = sortedArray.length;
    }

    public MaxHeap(Class<T> clazz, int capacity) {
        array = (T[]) Array.newInstance(clazz, capacity + 1);
        this.size = capacity;
        elementCount = 0;
    }

    public int find(T object) {
        if (elementCount == 0) {
            return -1;
        }
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(1);
        while (!queue.isEmpty()) {
            final int index = queue.remove();
            if (array[index].compareTo(object) == 0) {
                return index;
            }
            if (index * 2 < size && array[index * 2].compareTo(object) >= 0) {
                queue.add(index * 2);
            }
            if (index * 2 + 1 < size && array[index * 2 + 1].compareTo(object) >= 0) {
                queue.add(index * 2 + 1);
            }
        }
        return -1;
    }

    public boolean insert(T object) {
        if (elementCount >= size) {
            System.out.println("heap array is full");
            return false;
        }
        elementCount++;
        array[elementCount] = object;
        int i = elementCount;
        while (i > 1 && array[i].compareTo(array[i / 2]) > 0) {
            swap(array, i, i / 2);
            i = i / 2;
        }
        return true;
    }

    public boolean remove(T object) {
        /* 1.删除目标节点 */
        /* 2.将最后一个节点放到目标节点位置，顺次重新堆化 */
        final int targetIndex = find(object);
        if (targetIndex == -1) {
            return false;
        }
        array[targetIndex] = array[elementCount - 1];
        array[elementCount - 1] = null;
        elementCount -= 1;

        heapify(array, elementCount - 1, targetIndex);
        return false;
    }

    /**
     * 复制数组，将拷贝的数组作为堆成员
     *
     * @param array
     * @return
     */
    public static MaxHeap buildHeap(Comparable[] array) {
        final int length = array.length;
        final Class<?> clazz = array.getClass().getComponentType();
        Comparable[] realArray = (Comparable[]) Array.newInstance(clazz, length + 1);
        ArrayUtil.copy(array, 0, realArray, 1, length);
        for (int i = length / 2; i > 0; i--) {
            heapify(realArray, length, i);
        }
        return new MaxHeap(realArray);
    }

    /**
     * @param array ~
     * @param n     数组长度
     * @param i     当前节点在数组中的下标
     */
    public static void heapify(Comparable[] array, int n, int i) {
        while (true) {
            int maxPos = i;
            if (i * 2 <= n && array[i].compareTo(array[2 * i]) < 0) {
                maxPos = i * 2;
            }
            if (i * 2 + 1 <= n && array[maxPos].compareTo(array[2 * i + 1]) < 0) {
                maxPos = i * 2 + 1;
            }
            if (maxPos == i) {
                break;
            }
            swap(array, maxPos, i);
            i = maxPos;
        }
    }

    private static void swap(Object[] array, int index1, int index2) {
        Object temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    public static void main(String[] args) {
//        final MaxHeap maxHeap = makeHeap(new Integer[]{null, 7, 5, 19, 8, 4, 1, 20, 13, 16});
//        System.out.println(maxHeap);
        final Integer[] array = {7, 5, 19, 8, 4, 1, 20, 13, 16};
        final MaxHeap maxHeap = buildHeap(array);
        System.out.println(Arrays.toString(array));
        System.out.println(maxHeap);

        System.out.println(maxHeap.remove(13));
        System.out.println(maxHeap);
    }

}
