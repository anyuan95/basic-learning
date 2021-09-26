package org.example.basic.oj.leetcode.Q371;

import java.util.Random;

/**
 * 不使用加减乘除实现两数相加运算
 *
 * @author anyuan
 * @since 2021-09-26 08:40
 */
class Solution {

    /**
     * 另一种思路：计算每一位的值，然后全都并入到answer中。
     * 如果两个数该位上都为1，则answer该位记为t（t为进位标识），记一个进位标识。
     * 如果两个数该位上都为0，则answer该位记为t（t为进位标识）。
     * 否则（一个为1一个为0），则answer为1^t。
     *
     * @param a
     * @param b
     * @return
     */
    public int getSum(int a, int b) {
        // 拆分出所有需要进位的位，用按位与+左移1的方式拿到
        // 拆分出所有不需要进位的位，用异或的方式拿到
        // 由于进位不一定只进一次，所以要循环处理
        // 例如，第一次求出A和B相加时进位的部分，然后求出不进位的部分

        // 同1则1。同1的位与操作得到1之后向左移1位，就相当于进位了。
        // 这里获取的是要进位的位进位之后的位置。
        int carry = (a & b) << 1;
        // 异或，同则1（0+0=0,0+1=1,1+0=1,1+1=0），实际上就是不进位加。
        int noCarry = (a ^ b);
        // 不断执行以上操作，直到不需要进位为止。不需要进位时，就相当于noCarry和0相加，就是noCarry。
        while (carry != 0) {
            // 现在a+b就相当于，把carry和noCarry两个数相加
            a = carry;
            b = noCarry;
            carry = (a & b) << 1;
            noCarry = (a ^ b);
        }
        return noCarry;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        final Random rand = new Random();
        for (int i = 0; i < 100; i++) {
            int a = rand.nextInt();
            int b = rand.nextInt();
            if (a + b != solution.getSum(a, b)) {
                System.out.println("a=" + a + ", b=" + b);
                return;
            }
        }
        System.out.println("ok");
    }
}
