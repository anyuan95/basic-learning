package org.example.basic.oj.leetcode.Q762;

import java.util.Arrays;
import java.util.List;

/**
 * @author anyuan
 * @date 2022-04-05 23:08
 */
class Solution {

    private static final List<Integer> PRIMES = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19);

    public int countPrimeSetBits(int left, int right) {
        int answer = 0;
        for (int i = left; i <= right; i++) {
            if (PRIMES.contains(count(i))) {
                answer++;
            }
        }
        return answer;
    }

    private int count(int number) {
        int answer = 0;
        while (number != 0) {
            answer += (number & 1);
            number >>= 1;
        }
        return answer;
    }
}
