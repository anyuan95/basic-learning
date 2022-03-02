package org.example.basic.oj.leetcode.Q537;

/**
 * @author anyuan
 * @date 2022-02-25 19:59
 */
class Solution {
    /**
     * 格式： a + b i
     * <p>
     * <p>
     * 实部 是一个整数，取值范围是 [-100, 100]
     * 虚部 也是一个整数，取值范围是 [-100, 100]
     * i2 == -1
     * <p>
     * (a1+b1i) * (a2+b2i) = a1*a2 - b1*b2 + a1*b2i + a2*b1i
     *
     * @param num1
     * @param num2
     * @return
     */
    public String complexNumberMultiply(String num1, String num2) {
        final int addIndex1 = num1.indexOf('+');
        final Integer a1 = Integer.valueOf(num1.substring(0, addIndex1));
        final Integer b1 = Integer.valueOf(num1.substring(addIndex1+1, num1.length() - 1));
        final int addIndex2 = num2.indexOf('+');
        final Integer a2 = Integer.valueOf(num2.substring(0, addIndex2));
        final Integer b2 = Integer.valueOf(num2.substring(addIndex2+1, num2.length() - 1));
        final int az = a1 * a2 - b1 * b2;
        final int bz = a1 * b2 + a2 * b1;
        return az + "+" + bz + "i";
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.complexNumberMultiply("78+-76i", "-86+72i"));
    }
}
