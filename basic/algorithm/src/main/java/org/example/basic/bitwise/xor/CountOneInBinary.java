package org.example.basic.bitwise.xor;

/**
 * 問題：
 * 统计一个数二进制位中1的个数
 *
 * @author anyuan
 * @since 2021-07-29 10:10
 */
public class CountOneInBinary {

    /**
     * 最基本的思路，每次和1相与，如果结果为1说明最后一位为1
     *
     * @param number
     * @return
     */
    public int countOneInBinary(int number) {
        int count = 0;
        while (number != 0) {
            if ((number & 1) == 1) {
                count++;
            }
            number = number >> 1;
        }
        return count;
    }

    /**
     * 对最基本思路的优化，每次直接取最后一个1。
     * 效率比方法1高，方法1在数足够大的情况下最多需要遍历32次。
     *
     * @param number
     * @return
     */
    public int countOneInBinary_better(int number) {
        int count = 0;
        while (number != 0) {
            final int lastOne = number & (~number + 1);
            count++;
            // 这里必须进行异或。因为number可能是负数，如果直接减可能会出现错误。
            number ^= lastOne;
        }
        return count;
    }


    public static void main(String[] args) {
        System.out.println(new CountOneInBinary().countOneInBinary(32 - 1));
        System.out.println(new CountOneInBinary().countOneInBinary_better(32 - 1));

    }
}
