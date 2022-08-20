package org.example.basic.oj.leetcode.Q357;

class Solution {
    /**
     * 按排列组合的思路试试
     * <p>
     * 有n个位，
     * 例如n=3时，相当于先计算n=1，然后从10开始计算n=2，然后从100开始n=3
     * n=1时，10
     * n=2时，9*9 = 81 81+9 = 90
     * n=3时，9*9*8 = 648   648+81+9 = 738
     * n=4时，9*9*8*7
     *
     * @param n
     * @return
     */
    public int _countNumbersWithUniqueDigits(int n) {
        int answer = 0;
        for (int i = n; i >= 0; i--) {
            answer += count(i);
        }
        return answer;
    }

    private int count(int n) {
        if (n == 0) {
            return 1;
        } else if (n == 1) {
            return 9;
        }
        int temp = 9, answer = 9;
        while (n > 1) {
            answer *= temp;
            temp--;
            n--;
        }
        return answer;
    }

    public int countNumbersWithUniqueDigits(int n) {
        if (n == 0) {
            return 1;
        }
        int answer = 10;
        for (int i = 2, last = 9; i <= n; i++) {
            int cur = last * (10 - i + 1);
            answer += cur; last = cur;
        }
        return answer;
    }
}
