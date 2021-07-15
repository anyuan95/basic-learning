package org.example.basic.algorithm.sort;

/**
 * @author anyuan
 * @since 2021-03-05 14:40
 */
public abstract class Sort {

    protected static void swap(int[] array, int index1, int index2) {
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

}
