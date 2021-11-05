package org.example.basic.oj.leetcode.Q367;

/**
 * @author anyuan
 * @date 2021-11-04 20:33
 */
class Solution {
    /**
     * 判断完全平方数
     * <p>
     * 优化部分：
     * 找出完全平方数的必要条件：
     * 1.只有尾数是0,1,4,5,6,9的值才可能是完全平方数
     *
     * @param num
     * @return
     */
    public boolean isPerfectSquare(int num) {
        final int lastDigit = num % 10;
        if (lastDigit != 0 && lastDigit != 1 && lastDigit != 4 && lastDigit != 5 && lastDigit != 6 && lastDigit != 9) {
            return false;
        }
        int digitCount = 0, temp = num;
        while (temp != 0) {
            temp /= 10;
            digitCount++;
        }
        System.out.println(digitCount);
        int startNum = 1;
        for (int i = 0; i < digitCount / 2 - 1; i++) {
            startNum*=10;
        }
        int sqrt = startNum;
        while (sqrt * sqrt < num) {
            sqrt++;
        }
        return sqrt * sqrt == num;
    }

    /**
     * 确实，不知道怎么分，就二分。。
     *
     * @param num
     * @return
     */
    public boolean isPerfectSquare_bs(int num) {
        long left = 1, right = num;
        while (left <= right) {
            long middle = (left + right) / 2;
            if (middle * middle == num) {
                return true;
            } else if (middle * middle < num) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        return false;
    }



    public static void main(String[] args) {
//        System.out.println((int) Math.sqrt(Integer.MAX_VALUE));
        final Solution solution = new Solution();
//        System.out.println(solution.isPerfectSquare(10000));
//        System.out.println(solution.isPerfectSquare(100000));
//        System.out.println(solution.isPerfectSquare(16384));
        System.out.println(solution.isPerfectSquare_bs(1));
        System.out.println(solution.isPerfectSquare_bs(4));
        System.out.println(solution.isPerfectSquare_bs(1024));
        System.out.println(Math.sqrt(1024));
    }
}
