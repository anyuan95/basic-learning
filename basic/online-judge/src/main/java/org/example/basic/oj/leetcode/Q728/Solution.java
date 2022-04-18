package org.example.basic.oj.leetcode.Q728;

import java.util.ArrayList;
import java.util.List;

/**
 * @author anyuan
 * @date 2022-03-31 21:17
 */
class Solution {
    public List<Integer> selfDividingNumbers(int left, int right) {
        final List<Integer> answer = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            if (isSDN(i)) {
                answer.add(i);
            }
        }
        return answer;
    }

    private boolean isSDN(int n) {
        int temp = n;
        while (temp > 0) {
            if (temp % 10 == 0) {
                return false;
            }
            if (n % (temp % 10) != 0) {
                return false;
            }
            temp /= 10;
        }
        return true;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.isSDN(21));
    }
}
