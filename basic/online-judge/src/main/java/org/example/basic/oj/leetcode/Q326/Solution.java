package org.example.basic.oj.leetcode.Q326;

/**
 * @author anyuan
 * @since 2021-09-23 08:53
 */
class Solution {
    public boolean isPowerOfThree(int n) {
        if (n <= 0) {
            return false;
        }
        while (n % 3 == 0) {
            n /= 3;
        }
        return n == 1;
    }

    /**
     * 计算得到int数中最大的3的幂
     * 那么int中所有3的幂都应该能被这个数整除
     */
    private static final int MAX_POW_OF_3_INT = (int) Math.pow(3, 19);
    public boolean isPowerOfThree_no_loop(int n) {
        return n > 0 && MAX_POW_OF_3_INT % n == 0;
    }

    public static void main(String[] args) {
//        for (int i = 0; i < 100; i++) {
//            System.out.println("" + i + " = " + Integer.toString(i, 3) + " = " + Integer.toBinaryString(i));
//        }
        int j = (int) (1e9+7);
        System.out.println(j);
    }
}
