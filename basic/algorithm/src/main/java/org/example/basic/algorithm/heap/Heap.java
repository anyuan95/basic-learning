package org.example.basic.algorithm.heap;

import lombok.ToString;

/**
 * 堆
 *
 * @author anyuan
 * @since 2021-08-04 14:21
 */
public class Heap {

//    private int[] heap;
//    private int heapSize;


    protected void swap(int[] array, int index1, int index2) {
        if (index1 == index2) return;
        array[index1] = array[index1] ^ array[index2];
        array[index2] = array[index1] ^ array[index2];
        array[index1] = array[index1] ^ array[index2];
    }

    /**
     * 大根堆
     */
    @ToString
    private static class MaxHeap extends Heap {

        private int[] heap;
        private int heapSize;
        private final int heapSizeLimit;

        public MaxHeap(int heapSizeLimit) {
            this.heapSizeLimit = heapSizeLimit;
            this.heap = new int[heapSizeLimit];
            this.heapSize = 0;
        }

        private void add(int value) {
            if (heapSize == heapSizeLimit) {
                throw new IllegalStateException("full heap");
            }
            heap[heapSize] = value;
            floatUp(heapSize);
            heapSize++;
        }

        /**
         * 取出最大值
         */
        private int popMax() {
            int ans = heap[0];
            swap(heap, 0, heapSize - 1);
            heapSize--;
            sink(heap, 0, heapSize);
            heap[heapSize] = 0;
            return ans;
        }

        /**
         * 指定节点向上（根方向）浮动
         *
         * @param index
         */
        private void floatUp(/*int[] heap,*/ int index) {
            while (heap[index] > heap[(index - 1) / 2]) {
                swap(heap, index, (index - 1) / 2);
                index = (index - 1) >> 1;
            }
        }

        /**
         * 使指定节点的指向下（叶子方向）沉降
         *
         * @param heap     堆数组
         * @param index    需要沉降的节点（在数组中的）下标
         * @param heapSize 堆当前元素数量
         */
        // index的值与其子树中所有值进行比较，选择较大的一个与startIndex进行交换。
        // 以此类推，直到没有叶子节点，或者index位置的值大于每个子节点的值。
        private void sink(int[] heap, int index, int heapSize) {
            int leftIndex = (index << 1) + 1;
            while (leftIndex < heapSize) {
                int rightIndex = leftIndex + 1;
                // 取出三角中节点值最大的一个
                // 注意此处的条件，可能当前节点并没有右子节点（越界）。
                int largestIndex = rightIndex < heapSize && heap[leftIndex] < heap[rightIndex] ? rightIndex : leftIndex;
                largestIndex = heap[largestIndex] > heap[index] ? largestIndex : index;
                if (largestIndex == index) {
                    break;
                }
                swap(heap, index, largestIndex);
                index = largestIndex;
                leftIndex = (index << 1) + 1;
            }
        }

        public static void main(String[] args) {
            final MaxHeap maxHeap = new MaxHeap(10);
            maxHeap.add(1);
            maxHeap.add(2);
            maxHeap.add(3);
            maxHeap.add(4);
            maxHeap.add(5);
            maxHeap.add(6);
            maxHeap.add(7);
            maxHeap.add(8);
            maxHeap.add(9);
            System.out.println(maxHeap);
            System.out.println(maxHeap.popMax());
            System.out.println(maxHeap);
        }
    }

}
