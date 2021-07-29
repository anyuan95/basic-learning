package org.example.basic.bitwise.xor;

import java.util.Arrays;

/**
 * 现有一个数组，其中有两个值出现了奇数次，其他值都出现了偶数次，求出这两个值
 *
 * @author anyuan
 * @since 2021-07-29 09:35
 */
public class FindTwoOddOccurNumbers {

    /**
     * 思路：
     * 假定两个出现奇数次的数为a和b。
     * 因为只有a和b出现奇数次，所以数组中所有数相异或的结果就是a^b的结果，现在假设这个结果叫做xor。
     * 由于a和b一定不相等，所以xor一定不为0，所以xor二进制位一定有某一位为1。
     * 同理，因为xor中有某位为1，所以a和b中一定有且只有一个这一位为1，现在假定a是这个数。
     * 同理，将整个数组按照'这一位是否为1'分为两部分，那么a和b一定在不同的部分中。
     * 然后使用xor与数组中'这一位为1'的所有数进行异或运算，最终得到的相当于是(a^b)^a，即得到的是b。
     * 最后，由于xor=a^b，而我们获取了b的值，那么a=xor^b。
     *
     * @param numbers
     * @return
     */
    public int[] findTwoOddOccurNumbers(int[] numbers) {
        int xor = 0;
        for (int number : numbers) {
            xor ^= number;
        }
        final int lastOne = findLastOneInBinaryNumber(xor);
        int oneNumber = xor;
        for (int number : numbers) {
            if ((number & lastOne) != 0) {
                oneNumber ^= number;
            }
        }
        return new int[]{oneNumber, xor ^ oneNumber};
    }

    public int findLastOneInBinaryNumber(int number) {
        return number & ((~number) + 1);
    }

    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 4, 3, 4};
        System.out.println(Arrays.toString(new FindTwoOddOccurNumbers().findTwoOddOccurNumbers(numbers)));
    }

}
