package org.example.basic.oj.leetcode.Q519;

import java.util.Arrays;
import java.util.Random;

/**
 * @author anyuan
 * @since 2021-11-27 22:02
 */
class Solution_mle {

    int[][] numbers;
    int oneCount;

    public Solution_mle(int m, int n) {
        numbers = new int[m][n];
    }

    public int[] flip() {
        if (oneCount == numbers.length * numbers[0].length) {
            return null;
        }
        int mIndex = new Random().nextInt(numbers.length);
        int nIndex = new Random().nextInt(numbers[0].length);
        while (numbers[mIndex][nIndex] == 1) {
             mIndex = new Random().nextInt(numbers.length);
             nIndex = new Random().nextInt(numbers[0].length);
        }
        numbers[mIndex][nIndex] = 1;
        oneCount++;
        return new int[]{mIndex, nIndex};
    }

    public void reset() {
        for (int[] number : numbers) {
            Arrays.fill(number, 0);
        }
        oneCount = 0;
    }
}
