package org.example.basic.algorithm.heap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

/**
 * 加强堆
 * 在原生堆的基础上，添加反向索引表
 * <p>
 * 提供了快速删除指定位置元素的方法
 *
 * <p>
 * !!!! T必须是非基础类型
 *
 * @author anyuan
 * @since 2021-08-05 16:23
 */
public class HeapGreater<T> {

    private ArrayList<T> heap;
    private HashMap<T, Integer> indexMap;
    private int heapSize;
    private Comparator<? super T> comparator;

    public HeapGreater(Comparator<? super T> comparator) {
        this.comparator = comparator;
        this.heap = new ArrayList<>();
        this.indexMap = new HashMap<>();
        this.heapSize = 0;
    }

    private void swap(int index1, int index2) {
        final T o1 = heap.get(index1);
        final T o2 = heap.get(index2);
        heap.set(index1, o2);
        heap.set(index2, o1);
        // 注意，交换后，元素在索引表中的位置也要进行交换。
        indexMap.put(o1, index2);
        indexMap.put(o2, index1);
    }

    public boolean contains(T value) {
        return indexMap.containsKey(value);
    }

    public T peek() {
        return heap.get(0);
    }

    public T pop() {
        T answer = heap.get(0);
        swap(0, heapSize - 1);
        heapSize--;
        heapify(0);
        return answer;
    }

    public void push(T value) {
        heap.add(value);
        heapSize++;
        heapInsert(heapSize - 1);
    }

    public void remove(T value) {
        final Integer index = indexMap.remove(value);
        // 使用最后一个元素和目标位置进行交换，然后删除最后一个元素。
        final T lastElement = heap.get(heapSize - 1);
        // 然后删除最后一个元素。
        heap.remove(heapSize - 1);
        // 大小减小
        heapSize--;
        // 只有当删除的元素不是最后一个元素的时候才需要以下操作
        if (value != lastElement) {
            // 把最后一个元素放到被删除的元素的位置。
            heap.set(index, lastElement);
            // 更新最后一个元素在索引表中的信息。
            indexMap.put(lastElement, index);
            // 重新调整元素的位置
            resign(lastElement);
        }
    }

    /**
     * 重新调整某个节点的位置，保证堆仍然是大根堆
     *
     * @param value
     */
    public void resign(T value) {
        heapInsert(indexMap.get(value));
        heapify(indexMap.get(value));
    }

    /**
     * floatUp
     *
     * @param index
     */
    private void heapInsert(int index) {
        int parentNodeIndex = (index - 1) / 2;
        while (comparator.compare(heap.get(parentNodeIndex), heap.get(index)) > 0) {
            swap(index, parentNodeIndex);
            index = parentNodeIndex;
        }
    }

    /**
     * sink
     */
    private void heapify(int index) {
        int leftLeafIndex = index * 2 + 1;
        while (leftLeafIndex < heapSize) {
            int rightLeafIndex = leftLeafIndex + 1;
            int largestLeafIndex = rightLeafIndex < heapSize
                    && comparator.compare(heap.get(leftLeafIndex), heap.get(rightLeafIndex)) < 0
                    ? rightLeafIndex : leftLeafIndex;
            largestLeafIndex = comparator.compare(heap.get(leftLeafIndex), heap.get(index)) < 0
                    ? index : leftLeafIndex;
            if (index == largestLeafIndex) {
                return;
            }
            swap(index, largestLeafIndex);
            index = largestLeafIndex;
            leftLeafIndex = index * 2 + 1;
        }
    }
}
