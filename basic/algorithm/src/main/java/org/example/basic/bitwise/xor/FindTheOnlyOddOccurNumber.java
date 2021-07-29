package org.example.basic.bitwise.xor;

/**
 * @author anyuan
 * @since 2021-07-29 09:34
 */
public class FindTheOnlyOddOccurNumber {

    public int findTheOnlyOddNumber(int[] numbers) {
        int xor = 0;
        for (int number : numbers) {
            xor ^= number;
        }
        return xor;
    }

}
