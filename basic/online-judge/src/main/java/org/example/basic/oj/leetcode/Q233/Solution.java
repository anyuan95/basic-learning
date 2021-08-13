package org.example.basic.oj.leetcode.Q233;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 统计小于n的数中一共有多少个1
 *
 * @author anyuan
 * @since 2021-08-13 09:24
 */
class Solution {

    /**
     * 实际上就是每次求出每位为1的数有多少个，然后求和
     *
     * @param n
     * @return
     */
    public int countDigitOne(int n) {
        int count = 0;
        // digit: 倒数第d+1位
        int last = n, digitIndex = 0, lastDigit = last % 10;
        while (last != 0) {
            int currentDigit = last % 10;
            int pow = (int) Math.pow(10, digitIndex);
            if (currentDigit == 1) {
                count += (last / 10 + 1) * pow - (pow - 1 - n % pow);
            } else {
                count += (last / 10 + (currentDigit == 0 ? 0 : 1)) * pow;
            }
            last /= 10;
            digitIndex++;
        }
        return count;
    }

    public List<Integer> countDigitOne_force(int n) {
        List<Integer> answer = new ArrayList<>();
        int last = 0;
        for (int i = 0; i <= n; i++) {
            last += count1(i);
            answer.add(last);
        }
        return answer;
    }

    private int count1(int n) {
        int count = 0;
        int last = n;
        while (last != 0) {
            final int currentDigit = last % 10;
            if (currentDigit == 1) {
                count++;
            }
            last /= 10;
        }
//        if (count > 0) {
//            System.out.println(n + " has " + count + "'1's.");
//        }
        return count;
    }

    private boolean isKDigit1(int n, int k) {
        int last = n;
        for (int i = 0; i < k; i++) {
            last /= 10;
        }
        return last % 10 == 1;
    }

    private int countKDigitIs1(int n, int k) {
        int count = 0;
        for (int i = 0; i <= n; i++) {
            if (isKDigit1(i, k)) {
                count++;
            }
        }
        return count;
    }


    public static void main(String[] args) {
        final Solution solution = new Solution();
        final List<Integer> answers = solution.countDigitOne_force(10000);
//        System.out.println(answers.get(8243) == solution.countDigitOne(8243));

        for (int i = 0; i < 20; i++) {
            int n = Math.abs(new Random().nextInt(10000));
            if (answers.get(n) != solution.countDigitOne(n)) {
                System.out.println("nope, n=" + n);
                return;
            }
        }
        System.out.println("ok");

    }
}
