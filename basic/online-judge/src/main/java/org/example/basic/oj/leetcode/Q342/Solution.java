package org.example.basic.oj.leetcode.Q342;

/**
 * @author anyuan
 * @since 2021-08-30 16:24
 */
class Solution {

    /**
     * 首先是2的幂，就一定是0b00..00100..00
     * 然后&上0b01010101010101010101010101010101，得到它是否在4的幂的位上
     * @param n
     * @return
     */
    public boolean isPowerOfFour(int n) {
        if (n < 0 || (n & (n - 1)) != 0) {
            return false;
        }
        return (n & 0x55555555) != 0;
    }

    public boolean isPowerOfFour_x(int n) {
        if (n <= 0) {
            return false;
        } else if (n == 1) {
            return true;
        }
        // 倒数第二位是1，一定不是4^k
        if ((((n >> 1) & 1) == 1) || (((n >> 3) & 1) == 1)) {
            return false;
        }
        // 最后一位不是1，且倒数第二位不是1（上一个if判断了），且剩下的部分和-1相与结果为0
        if (((n & 1) != 1) && ((n >> 2) & ((n >> 2) - 1)) == 0) {
            return true;
        }
        return false;
    }

    private boolean isPowerOf4(int n) {
        if (n <= 0) {
            return false;
        }
        while (n % 4 == 0) {
            n /= 4;
        }
        return n == 1;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
//        final Random random = new Random();
        for (int i = 0; i < 10000; i++) {
//            final int num = random.nextInt();
            int num = i;
            if (solution.isPowerOfFour(num) != solution.isPowerOf4(num)) {
                System.out.println(num);
                return;
            }
        }
        System.out.println("ok");

//        System.out.println(solution.isPowerOfFour(8));
//        System.out.println(solution.isPowerOf4(8));
    }
}
