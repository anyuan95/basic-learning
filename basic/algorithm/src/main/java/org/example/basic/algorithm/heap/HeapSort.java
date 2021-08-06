package org.example.basic.algorithm.heap;

import org.example.basic.helper.ArrayHelper;

import java.util.Arrays;

/**
 * @author anyuan
 * @since 2021-08-04 17:19
 */
public class HeapSort {

    /**
     * 建堆操作，有两种方式：
     * 1.从上往下，对每个元素进行上浮操作，时间复杂度On*logn
     * 2.从下往上，对每个元素进行下沉操作，时间复杂度为On
     * PS.方法2只有在堆排序（即直接提供整个数组）时才可用
     *
     * 由于当前得到的大顶堆中，0位置一定是数组中最大值，
     * 故每次将0位置的值与数组末尾有序区的前一个值进行交换，然后将0位置的值再进行下沉（下沉后，数组无序区仍会是大顶堆）。
     *
     *
     * @param nums
     */
    private void sort(int[] nums) {
        int heapSize = nums.length;
        // 构建大顶堆
        // 从下往上（从后往前）逐个进行下沉操作
        for (int index = nums.length - 1; index >= 0; index--) {
            sink(nums, index, heapSize);
        }
        // 每次将最大值移到数组末尾的有序区的头部
        for (int index = nums.length - 1; index >= 0; index--) {
            swap(nums, 0, index);
            sink(nums, 0, --heapSize);
        }
    }

    /**
     * heapify
     * @param nums
     * @param index
     * @param heapSize
     */
    private void sink(int[] nums, int index, int heapSize) {
        int leftLeafIndex = index * 2 + 1;
        while (leftLeafIndex < heapSize) {
            int rightLeafIndex = leftLeafIndex + 1;
            int maxValueIndexInTriangle = rightLeafIndex < heapSize && nums[leftLeafIndex] < nums[rightLeafIndex] ? rightLeafIndex : leftLeafIndex;
            maxValueIndexInTriangle = nums[index] < nums[maxValueIndexInTriangle] ? maxValueIndexInTriangle : index;
            if (index == maxValueIndexInTriangle) {
                break;
            }
            swap(nums, index, maxValueIndexInTriangle);
            index = maxValueIndexInTriangle;
            leftLeafIndex = index * 2 + 1;
        }
    }

    private void swap(int[] nums, int index1, int index2) {
        if (index1 == index2) return;
        nums[index1] = nums[index1] ^ nums[index2];
        nums[index2] = nums[index1] ^ nums[index2];
        nums[index1] = nums[index1] ^ nums[index2];
    }

    public static void main(String[] args) {
        int[] array = ArrayHelper.generateRandomArray(20, 10);
        System.out.println(Arrays.toString(array));
        final HeapSort heapSort = new HeapSort();
        heapSort.sort(array);
        System.out.println(Arrays.toString(array));
    }
}
