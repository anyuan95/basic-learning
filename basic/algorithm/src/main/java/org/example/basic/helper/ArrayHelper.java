package org.example.basic.helper;

/**
 * @author anyuan
 * @since 2021-08-02 11:43
 */
public class ArrayHelper {

    public static int[] generateRandomArray(int maxSize, int maxValue) {
        final int size = Math.abs((int) (Math.random() * (maxSize + 1)));
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = (int) (Math.random() * (maxValue + 1)) - (int) (Math.random() * maxValue);
        }
        return array;
    }
}
