package org.example.basic.oj.leetcode.Q29;

class Solution_minus_pow2n {
    /**
     * 思路2：不断地从a中减去b的2^n倍数
     * 将减掉的倍数加到ans上
     *
     * @param dividend
     * @param divisor
     * @return
     */
    public int divide(int dividend, int divisor) {
        long a = dividend, b = divisor;
        boolean isNegative = false;
        if (a < 0 && b > 0) {
            isNegative = true;
            a = -a;
        } else if (a > 0 && b < 0) {
            isNegative = true;
            b = -b;
        } else if (a < 0 && b < 0) {
            a = -a;
            b = -b;
        }
        long answer = div(a, b);
        answer = isNegative ? -answer : answer;
        return answer > Integer.MAX_VALUE || answer < Integer.MIN_VALUE ? Integer.MAX_VALUE : (int) answer;
    }

    private long div(long a, long b) {
        if (a < b) return 0;
        long answer = 1;
        long bx = b;
        while (a >= bx + bx) {
            answer += answer;
            bx += bx;
        }
        return answer + div(a - bx, b);
    }

    public static void main(String[] args) {
        Solution_minus_pow2n solution = new Solution_minus_pow2n();
        System.out.println(solution.divide(60, 8));
        System.out.println(solution.divide(Integer.MIN_VALUE, -1));
    }
}
