package org.example.basic.oj.leetcode.Q400;

/**
 * @author anyuan
 * @date 2021-11-30 11:33
 */
class Solution {
    /**
     * 查找递增数列的第n位（从1开始）
     * 找规律
     * <p>
     * 1-9，9个1位数
     * 10-99，90个2位数
     * 100-999，900个3位数
     * 以此类推
     * <p>
     * 然后找出确定值的方式
     * 比如找出100-999范围的第k位所在的数，这个数就是100+k/3，位数再计算即可
     *
     * @param n
     * @return
     */
    public int findNthDigit(int n) {
        int digit = 1;
        // 先计算出目标位所在的数的位数
        while (n > nDigitNumberCount[digit] * digit) {
            n -= nDigitNumberCount[digit] * digit;
            digit++;
        }
        // 然后找到是n位数里的第几个
        final int indexInSuchDigitArea = (n-1) / digit;
        // 然后找到这个数
        int targetNumber = (int) (Math.pow(10, digit - 1) + indexInSuchDigitArea);
        // 找到是第几位
        int targetDigit = (n-1) % digit;
        // 最后在这个数上找到第targetDigit位
        return findNthDigit(targetNumber, targetDigit, digit);
    }

    private int findNthDigit(int val, int n, int digit) {
       // n从0开始
        int temp = digit - n;
        while (temp > 1) {
            val /= 10;
            temp--;
        }
        return val % 10;
    }

    private static final long[] nDigitNumberCount = new long[30];

    static {
        int digit = 1;
        long count = 9, sum = 0;
        while (digit < 30) {
            nDigitNumberCount[digit] = (int) count;
            sum += count;
            digit += 1;
            count *= 10;
        }
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
//        System.out.println(solution.findNthDigit(64, 0, 2));
//        System.out.println(solution.findNthDigit(64, 1, 2));
//        System.out.println(solution.findNthDigit(9, 0, 1));


        System.out.println(solution.findNthDigit(10));


        System.out.println(solution.findNthDigit(9));
        //123456789101112131415
        System.out.println(solution.findNthDigit(19));
//        final StringBuilder builder = new StringBuilder();
//        int i = 1;
//        while (builder.length() < 119) {
//            builder.append(i++);
//        }
//        System.out.println(builder);
//        System.out.println(builder.length());
//        System.out.println(builder.charAt(118));
//        System.out.println(i);


        System.out.println(solution.findNthDigit(119));
        System.out.println(solution.findNthDigit(1119));
        System.out.println(solution.findNthDigit(999));
        System.out.println(solution.findNthDigit(1000));
        System.out.println(solution.findNthDigit(10_0000_0000));
//        10_0000_0000
    }
}
